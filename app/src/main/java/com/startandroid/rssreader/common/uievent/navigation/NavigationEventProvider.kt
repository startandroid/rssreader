package com.startandroid.rssreader.common.uievent.navigation

import kotlinx.coroutines.flow.Flow

interface NavigationEventProvider {
    fun navigationEvents(): Flow<NavigationEvent>
}