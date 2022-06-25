package com.store.favquotes.feature.quotes.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.store.favquotes.feature.quotes.domain.model.Quote
import com.store.favquotes.feature.quotes.domain.usecase.GetQuotesListSearchResultsUseCase
import com.store.favquotes.feature.quotes.ui.actions.SearchQuotesStateEffect.Action
import com.store.favquotes.feature.quotes.ui.actions.SearchQuotesStateEffect.Event
import com.store.favquotes.feature.quotes.ui.actions.SearchQuotesStateEffect.Event.Navigate.Back
import com.store.favquotes.feature.quotes.ui.actions.SearchQuotesStateEffect.State
import com.store.favquotes.network.ResultWrapper
import com.store.favquotes.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val debounceTimeoutConst = 700L

@HiltViewModel
class SearchQuotesViewModel @Inject constructor(
    private val getQuotesListSearchResultsUseCase: GetQuotesListSearchResultsUseCase
) : BaseViewModel<State, Event, Action>() {
    override val state: MutableStateFlow<State> =
        MutableStateFlow(State())

    override fun onAction(action: Action) {
        when (action) {
            Action.OnBack -> sendInScope(Back)
            is Action.OnSearchTerm -> onSearchTermChanged(action.searchTerm)
        }
    }

    private fun onSearchTermChanged(searchTerm: String) {
        state.update { it.copy(searchTerm = searchTerm) }

        if(searchTerm.isEmpty()) {
            state.update { it.copy(quotes = listOf()) }
        }
    }

    init {
        debounceSearchObserver()
    }

    private fun debounceSearchObserver() {
        viewModelScope.launch(Dispatchers.Default) {
            state.map { it.searchTerm }
                .debounce(debounceTimeoutConst)
                .distinctUntilChanged()
                .collect {
                    if (it.isBlank()) return@collect

                    when (val response = getQuotesListSearchResultsUseCase(it)) {
                        is ResultWrapper.Success -> onSuccess(response.value)
                        else -> onError()
                    }
                }
        }
    }

    private fun onSuccess(quotes: List<Quote>) {
        state.update { it.copy(isLoading = false, quotes = quotes) }
    }

    private fun onError() {
        state.update { it.copy(isLoading = false, quotes = listOf()) }
    }
}
