package com.store.favquotes.feature.quotes.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.store.favquotes.feature.quotes.domain.usecase.GetRandomQuoteUseCase
import com.store.favquotes.feature.quotes.ui.actions.MainScreenStateEffect.Action
import com.store.favquotes.feature.quotes.ui.actions.MainScreenStateEffect.Event
import com.store.favquotes.feature.quotes.ui.actions.MainScreenStateEffect.Event.Navigate.*
import com.store.favquotes.feature.quotes.ui.actions.MainScreenStateEffect.State
import com.store.favquotes.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase,
): BaseViewModel<State, Event, Action>() {
    override val state: MutableStateFlow<State> = MutableStateFlow(State())

    override fun onAction(action: Action) {
        when (action) {
            Action.OnLogin -> sendInScope(ToLogin)
            Action.OnPublicQuotes -> sendInScope(ToPublicQuotes)
            Action.OnSearchQuotes -> sendInScope(ToSearchQuotes)
        }
    }

    init {
        getRandomQuote()
    }

    private fun getRandomQuote() {
        viewModelScope.launch(Dispatchers.Default) {
            val response = getRandomQuoteUseCase()
            Timber.d("")
        }
    }
}
