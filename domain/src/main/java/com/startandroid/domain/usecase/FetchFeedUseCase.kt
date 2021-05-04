package com.startandroid.domain.usecase

import com.startandroid.domain.dto.Feed
import com.startandroid.domain.repository.FeedRepository

class FetchFeedUseCase(private val feedRepository: FeedRepository) {

    suspend fun invoke(url: String): Feed {
        return feedRepository.fetchFeed(url)
    }

}