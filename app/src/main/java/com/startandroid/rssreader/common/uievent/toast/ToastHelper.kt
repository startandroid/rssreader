package com.startandroid.rssreader.common

import com.startandroid.rssreader.common.uievent.toast.ToastEvent
import com.startandroid.rssreader.common.uievent.toast.ToastEventProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class ToastHelper @Inject constructor(): ToastEventProvider {

    private val _events = MutableSharedFlow<ToastEvent>(extraBufferCapacity = 1)
    private val events = _events.asSharedFlow()

    override fun toastEvents(): Flow<ToastEvent> = events

    fun show(messageText: String) {
        postEvent(ToastEvent(messageText = messageText))
    }

    fun show(messageResId: Int) {
        postEvent(ToastEvent(messageResId = messageResId))
    }

    private fun postEvent(event: ToastEvent) {
        _events.tryEmit(event)
    }
}



