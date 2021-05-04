package com.startandroid.rssreader.di

import com.startandroid.domain.repository.FeedRepository
import com.startandroid.domain.usecase.FetchFeedUseCase
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

}