package com.store.favquotes.feature.quotes.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.store.favquotes.feature.quotes.domain.model.LoginResponse
import com.store.favquotes.feature.quotes.domain.usecase.LoginUseCase
import com.store.favquotes.feature.quotes.ui.actions.LoginStateEffect.Action
import com.store.favquotes.feature.quotes.ui.actions.LoginStateEffect.Event
import com.store.favquotes.feature.quotes.ui.actions.LoginStateEffect.Event.Navigate.Back
import com.store.favquotes.feature.quotes.ui.actions.LoginStateEffect.State
import com.store.favquotes.network.ResultWrapper
import com.store.favquotes.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel<State, Event, Action>() {
    override val state: MutableStateFlow<State> =
        MutableStateFlow(State())

    override fun onAction(action: Action) {
        when (action) {
            Action.OnBack -> sendInScope(Back)
            Action.OnLogin -> login()
            is Action.OnPasswordValueChanged -> onPasswordValueChanged(action.password)
            is Action.OnUsernameValueChanged -> onUsernameValueChanged(action.userName)
            Action.DismissSignInDialog -> dismissSignInDialog()
        }
    }

    private fun dismissSignInDialog() {
        state.update { it.copy(shouldShowSignedInDialog = false) }
        sendInScope(Back)
    }

    private fun onUsernameValueChanged(userNameOrEmail: String) {
        state.update { it.copy(userNameOrEmail = userNameOrEmail) }
    }

    private fun onPasswordValueChanged(password: String) {
        state.update { it.copy(password = password) }
    }

    private fun login() {
        viewModelScope.launch(Dispatchers.Default) {
            when (val response = loginUseCase(
                userNameOrEmail = state.value.userNameOrEmail,
                password = state.value.password,
            )) {
                is ResultWrapper.Success -> onSuccess(response.value)
                else -> onError()
            }
        }
    }

    private fun onSuccess(loginResponse: LoginResponse) {
        if(loginResponse.errorMessage != null) {
            state.update { it.copy(hasError = true, errorMessage = loginResponse.errorMessage) }
        } else {
            state.update { it.copy(hasError = false, errorMessage = null, shouldShowSignedInDialog = true) }
        }
    }

    private fun onError() {
        state.update { it.copy(hasError = true, errorMessage = null) }
    }
}
