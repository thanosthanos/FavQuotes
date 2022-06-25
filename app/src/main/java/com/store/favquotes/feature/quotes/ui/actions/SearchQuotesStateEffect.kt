package com.store.favquotes.feature.quotes.ui.actions

import com.store.favquotes.feature.quotes.domain.model.Quote

object SearchQuotesStateEffect {

    data class State(
        val searchTerm: String = "",
        val quotes: List<Quote> = listOf(),
        val isLoading: Boolean = false,
        val hasError: Boolean = false, // TODO delete
    )

    sealed interface Event {
        sealed interface Navigate : Event {
            object Back: Navigate
        }
    }

    sealed interface Action {
        object OnBack : Action
        data class OnSearchTerm(val searchTerm: String) : Action
    }

}
