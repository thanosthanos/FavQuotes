package com.store.favquotes.feature.quotes.ui.actions

object LoginStateEffect {

    data class State(
        val userNameOrEmail: String = "",
        val password: String = "",
        val isLoading: Boolean = false,
        val hasError: Boolean = false,
    ) {
        fun isLoginEnabled(): Boolean = userNameOrEmail.isEmpty().not() && password.isEmpty().not()
    }

    sealed interface Event {
        sealed interface Navigate : Event {
            object Back: Navigate
        }
    }

    sealed interface Action {
        object OnLogin : Action
        object OnBack : Action
        data class OnUsernameValueChanged(val userName: String) : Action
        data class OnPasswordValueChanged(val password: String) : Action
    }

}
