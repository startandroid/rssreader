package com.startandroid.domain.usecase

import com.startandroid.domain.repository.FeedRepository

class RefreshItemsUseCase(private val feedRepository: FeedRepository) {

    suspend fun invoke() {
        feedRepository.refreshItems()
    }

}