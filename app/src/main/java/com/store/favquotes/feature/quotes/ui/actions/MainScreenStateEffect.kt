package com.store.favquotes.feature.quotes.ui.actions

object MainScreenStateEffect {

    data class State(
        val quote: String? = null,
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
