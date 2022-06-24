package com.store.favquotes.feature.quotes.ui.actions

import com.store.favquotes.feature.quotes.domain.model.Quote

object MainScreenStateEffect {

    data class State(
        val quote: Quote? = null,
        val isLoading: Boolean = false,
        val hasError: Boolean = false,
    )

    sealed interface Event {
        sealed interface Navigate : Event {
            object ToPublicQuotes: Navigate
            object ToSearchQuotes: Navigate
            object ToLogin: Navigate
        }
    }

    sealed interface Action {
        object OnPublicQuotes : Action
        object OnSearchQuotes : Action
        object OnLogin : Action
    }
}
