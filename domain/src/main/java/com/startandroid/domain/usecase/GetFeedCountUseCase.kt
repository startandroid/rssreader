package com.startandroid.domain.usecase

import com.startandroid.domain.repository.FeedRepository
import kotlinx.coroutines.flow.Flow

class GetFeedCountUseCase(private val feedRepository: FeedRepository) {

    fun invoke(): Flow<Int> {
        return feedRepository.getFeedCount()
    }

}