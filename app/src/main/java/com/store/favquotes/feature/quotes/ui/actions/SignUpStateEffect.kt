package com.store.favquotes.feature.quotes.ui.actions

import com.store.favquotes.extension.isEmailValid
import com.store.favquotes.extension.isPasswordValid
import com.store.favquotes.extension.isUserNameValid

object SignUpStateEffect {

    data class State(
        val userName: String = "",
        val email: String = "",
        val password: String = "",
        val isLoading: Boolean = false,
        val hasError: Boolean = false,
        val errorMessage: String? = null,
        val shouldShowSignedUpDialog: Boolean = false,
    ) {
        fun isSignUpEnabled(): Boolean =
            userName.isUserNameValid() && (email.isEmpty() || email.isEmailValid())
                    && password.isPasswordValid() && isLoading.not()
    }

    sealed interface Event {
        sealed interface Navigate : Event {
            object Back : Navigate
        }
    }

    sealed interface Action {
        object OnSignUp : Action
        object OnBack : Action
        object DismissSignUpDialog : Action
        data class OnUsernameValueChanged(val userName: String) : Action
        data class OnEmailValueChanged(val email: String) : Action
        data class OnPasswordValueChanged(val password: String) : Action
    }

}
