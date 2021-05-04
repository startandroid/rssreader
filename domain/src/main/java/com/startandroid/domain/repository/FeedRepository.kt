package com.startandroid.domain.repository

import com.startandroid.domain.dto.Feed

interface FeedRepository {
    suspend fun fetchFeed(url: String): Feed
}