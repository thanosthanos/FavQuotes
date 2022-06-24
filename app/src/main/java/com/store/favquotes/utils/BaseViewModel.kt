package com.store.favquotes.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

interface WithStateEventAction<State, Event, Action> {
    val state: MutableStateFlow<State>
    val event: Channel<Event>
    fun onAction(action: Action)
}

abstract class BaseViewModel<State, Event, Action> :
    ViewModel(),
    WithStateEventAction<State, Event, Action> {

    override val event: Channel<Event> = Channel()

    fun sendInScope(newEvent: Event) {
        viewModelScope.launch(Dispatchers.Default) { event.send(newEvent) }
    }
}
