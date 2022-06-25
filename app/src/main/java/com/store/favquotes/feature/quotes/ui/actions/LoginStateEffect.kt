package com.store.favquotes.feature.quotes.ui.actions

object LoginStateEffect {

    data class State(
        val userNameOrEmail: String = "",
        val password: String = "",
        val isLoading: Boolean = false,
        val hasError: Boolean = false,
        val errorMessage: String? = null,
        val shouldShowSignedInDialog: Boolean = false,
    ) {
        fun isLoginEnabled(): Boolean = userNameOrEmail.isEmpty().not() && password.isEmpty().not() && isLoading.not()
    }

    sealed interface Event {
        sealed interface Navigate : Event {
            object Back: Navigate
        }
    }

    sealed interface Action {
        object OnLogin : Action
        object OnBack : Action
        object DismissSignInDialog : Action
        data class OnUsernameValueChanged(val userName: String) : Action
        data class OnPasswordValueChanged(val password: String) : Action
    }

}
