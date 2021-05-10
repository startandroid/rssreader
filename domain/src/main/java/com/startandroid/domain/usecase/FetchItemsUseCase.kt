package com.startandroid.domain.usecase

import com.startandroid.domain.dto.Item
import com.startandroid.domain.repository.FeedRepository

class FetchItemsUseCase(private val feedRepository: FeedRepository) {

    suspend fun invoke(url: String): List<Item> {
        return feedRepository.fetchItems(url)
    }

}