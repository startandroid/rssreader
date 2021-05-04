package com.startandroid.rssreader.common.uievent.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import kotlin.reflect.KClass

class NavigationHelper @Inject constructor(): NavigationEventProvider {

    private val _events = MutableSharedFlow<NavigationEvent>(extraBufferCapacity = 1)
    private val events = _events.asSharedFlow()

    override fun navigationEvents(): Flow<NavigationEvent> = events

    fun addFragment(cls: KClass<out Fragment>, arguments: Bundle? = null) {
        postEvent(NavigationFragmentEvent(cls, NavigationFragmentAction.ADD, arguments = arguments))
    }

    fun replaceFragment(cls: KClass<out Fragment>, arguments: Bundle? = null) {
        postEvent(NavigationFragmentEvent(cls, NavigationFragmentAction.REPLACE, arguments = arguments))
    }

    private fun postEvent(event: NavigationEvent) {
        _events.tryEmit(event)
    }


}