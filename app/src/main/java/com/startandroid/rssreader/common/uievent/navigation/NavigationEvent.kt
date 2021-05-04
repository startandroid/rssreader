package com.startandroid.rssreader.common.uievent.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlin.reflect.KClass

sealed class NavigationEvent
data class NavigationFragmentEvent(
    val kls: KClass<out Fragment>,
    val action: NavigationFragmentAction = NavigationFragmentAction.REPLACE,
    val arguments: Bundle? = null
): NavigationEvent()

enum class NavigationFragmentAction {
    ADD, REPLACE
}
