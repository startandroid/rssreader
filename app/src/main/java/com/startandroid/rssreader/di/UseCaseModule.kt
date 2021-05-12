package com.startandroid.rssreader.di

import com.startandroid.domain.repository.FeedRepository
import com.startandroid.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideFetchFeedUseCase(feedRepository: FeedRepository): FetchFeedUseCase {
        return FetchFeedUseCase(feedRepository)
    }

    @Provides
    fun provideFetchItemsUseCase(feedRepository: FeedRepository): FetchItemsUseCase {
        return FetchItemsUseCase(feedRepository)
    }

    @Provides
    fun provideAddFeedUseCase(feedRepository: FeedRepository): AddFeedUseCase {
        return AddFeedUseCase(feedRepository)
    }

    @Provides
    fun provideGetFeedListUseCase(feedRepository: FeedRepository): GetFeedListUseCase {
        return GetFeedListUseCase(feedRepository)
    }

    @Provides
    fun provideRefreshItemsUseCase(feedRepository: FeedRepository): RefreshItemsUseCase {
        return RefreshItemsUseCase(feedRepository)
    }

}