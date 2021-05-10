package com.startandroid.domain.repository

import com.startandroid.domain.dto.Feed
import com.startandroid.domain.dto.Item
import kotlinx.coroutines.flow.Flow

interface FeedRepository {
    suspend fun fetchFeed(url: String): Feed
    suspend fun fetchItems(url: String): List<Item>
    suspend fun addFeed(url: String)
    fun getFeedList(): Flow<List<Feed>>
    suspend fun removeItem(link: String)
    fun getFeedCount(): Flow<Int>
    suspend fun clean()
}