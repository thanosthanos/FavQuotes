package com.store.favquotes.feature.quotes.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.store.favquotes.feature.quotes.domain.model.Quote
import com.store.favquotes.feature.quotes.domain.usecase.GetPublicQuotesUseCase
import com.store.favquotes.feature.quotes.ui.actions.ListOfQuotesStateEffect.Action
import com.store.favquotes.feature.quotes.ui.actions.ListOfQuotesStateEffect.Event
import com.store.favquotes.feature.quotes.ui.actions.ListOfQuotesStateEffect.Event.Navigate.Back
import com.store.favquotes.feature.quotes.ui.actions.ListOfQuotesStateEffect.State
import com.store.favquotes.network.ResultWrapper
import com.store.favquotes.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListOfQuotesViewModel @Inject constructor(
    private val getPublicQuotesUseCase: GetPublicQuotesUseCase
) : BaseViewModel<State, Event, Action>() {
    override val state: MutableStateFlow<State> =
        MutableStateFlow(State())

    override fun onAction(action: Action) {
        when (action) {
            Action.OnBack -> sendInScope(Back)
        }
    }

    init {
        getQuotes()
    }

    private fun getQuotes() {
        state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.Default) {
            when (val response = getPublicQuotesUseCase()) {
                is ResultWrapper.Success -> onSuccess(response.value)
                else -> onError()
            }
        }
    }

    private fun onSuccess(quotes: List<Quote>) {
        state.update { it.copy(isLoading = false, quotes = quotes) }
    }

    private fun onError() {
        state.update { it.copy(isLoading = false, hasError = true) }
    }

}
