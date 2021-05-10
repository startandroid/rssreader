package com.startandroid.domain.usecase

import com.startandroid.domain.dto.Feed
import com.startandroid.domain.repository.FeedRepository
import kotlinx.coroutines.flow.Flow

class GetFeedListUseCase(
    private val feedRepository: FeedRepository
) {

    fun invoke(): Flow<List<Feed>> {
        return feedRepository.getFeedList()
    }


}