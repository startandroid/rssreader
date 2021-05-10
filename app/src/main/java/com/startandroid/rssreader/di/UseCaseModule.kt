package com.startandroid.rssreader.di

import com.startandroid.domain.repository.FeedRepository
import com.startandroid.domain.usecase.AddFeedUseCase
import com.startandroid.domain.usecase.FetchFeedUseCase
import com.startandroid.domain.usecase.FetchItemsUseCase
import com.startandroid.domain.usecase.GetFeedListUseCase
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

}