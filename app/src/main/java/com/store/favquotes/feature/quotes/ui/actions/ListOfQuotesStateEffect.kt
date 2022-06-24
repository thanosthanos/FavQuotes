package com.store.favquotes.feature.quotes.ui.actions

import com.store.favquotes.feature.quotes.domain.model.Quote

object ListOfQuotesStateEffect {

    data class State(
        val quotes: List<Quote> = listOf(),
        val isLoading: Boolean = false,
        val hasError: Boolean = false,
    )

    sealed interface Event {
        sealed interface Navigate : Event {
            object Back: Navigate
        }
    }

    sealed interface Action {
        object OnBack : Action
    }

}
