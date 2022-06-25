package com.store.favquotes.feature.quotes.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.store.favquotes.feature.quotes.domain.model.SignUpResponse
import com.store.favquotes.feature.quotes.domain.usecase.SignUpUseCase
import com.store.favquotes.feature.quotes.ui.actions.SignUpStateEffect.Action
import com.store.favquotes.feature.quotes.ui.actions.SignUpStateEffect.Event
import com.store.favquotes.feature.quotes.ui.actions.SignUpStateEffect.Event.Navigate.Back
import com.store.favquotes.feature.quotes.ui.actions.SignUpStateEffect.State
import com.store.favquotes.network.ResultWrapper
import com.store.favquotes.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
) : BaseViewModel<State, Event, Action>() {
    override val state: MutableStateFlow<State> =
       MutableStateFlow(State())

    override fun onAction(action: Action) {
        when(action) {
            Action.DismissSignUpDialog -> dismissSignUpDialog()
            Action.OnBack -> sendInScope(Back)
            is Action.OnEmailValueChanged -> onEmailValueChanged(action.email)
            Action.OnSignUp -> signUp()
            is Action.OnPasswordValueChanged -> onPasswordValueChanged(action.password)
            is Action.OnUsernameValueChanged -> onUsernameValueChanged(action.userName)
        }
    }

    private fun dismissSignUpDialog() {
        state.update { it.copy(shouldShowSignedUpDialog = false) }
        sendInScope(Back)
    }

    private fun onUsernameValueChanged(userName: String) {
        state.update { it.copy(userName = userName) }
    }

    private fun onEmailValueChanged(email: String) {
        state.update { it.copy(email = email) }
    }

    private fun onPasswordValueChanged(password: String) {
        state.update { it.copy(password = password) }
    }

    private fun signUp() {
        state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.Default) {
            when (val response = signUpUseCase(
                username = state.value.userName,
                email = state.value.email,
                password = state.value.password,
            )) {
                is ResultWrapper.Success -> onSuccess(response.value)
                else -> onError()
            }
        }
    }

    private fun onSuccess(loginResponse: SignUpResponse) {
        if(loginResponse.errorMessage != null) {
            state.update { it.copy(isLoading = false, hasError = true, errorMessage = loginResponse.errorMessage) }
        } else {
            state.update { it.copy(isLoading = false, hasError = false, errorMessage = null, shouldShowSignedUpDialog = true) }
        }
    }

    private fun onError() {
        state.update { it.copy(isLoading = false, hasError = true, errorMessage = null) }
    }

}
