package com.startandroid.rssreader.common.uievent.toast

import kotlinx.coroutines.flow.Flow

interface ToastEventProvider {
    fun toastEvents(): Flow<ToastEvent>
}