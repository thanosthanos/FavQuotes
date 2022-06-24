package com.store.favquotes.feature.quotes.ui.viewmodel

import com.store.favquotes.feature.quotes.ui.actions.MainScreenStateEffect.Action
import com.store.favquotes.feature.quotes.ui.actions.MainScreenStateEffect.Event
import com.store.favquotes.feature.quotes.ui.actions.MainScreenStateEffect.Event.Navigate.*
import com.store.favquotes.feature.quotes.ui.actions.MainScreenStateEffect.State
import com.store.favquotes.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

): BaseViewModel<State, Event, Action>() {
    override val state: MutableStateFlow<State> = MutableStateFlow(State())

    override fun onAction(action: Action) {
        when (action) {
            Action.OnLogin -> sendInScope(ToLogin)
            Action.OnPublicQuotes -> sendInScope(ToPublicQuotes)
            Action.OnSearchQuotes -> sendInScope(ToSearchQuotes)
        }
    }

}
