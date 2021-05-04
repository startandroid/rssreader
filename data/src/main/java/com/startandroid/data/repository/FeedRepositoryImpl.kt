package com.startandroid.data.repository

import android.util.Log
import com.startandroid.data.api.FeedServiceApi
import com.startandroid.data.mapping.FeedMapperApiToUi
import com.startandroid.domain.dto.Feed
import com.startandroid.domain.repository.FeedRepository

class FeedRepositoryImpl(
    private val feedServiceApi: FeedServiceApi,
    private val feedMapperApiToUi: FeedMapperApiToUi
): FeedRepository {

    override suspend fun fetchFeed(url: String): Feed {
        val feedApi = feedServiceApi.fetchFeed(url)
        return feedMapperApiToUi.map(feedApi)
    }

}