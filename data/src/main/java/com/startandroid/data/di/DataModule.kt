package com.startandroid.data.di

import com.startandroid.data.api.FeedServiceApi
import com.startandroid.data.mapping.FeedMapperApiToUi
import com.startandroid.data.repository.FeedRepositoryImpl
import com.startandroid.domain.repository.FeedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jaxb.JaxbConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideFeedRepository(feedServiceApi: FeedServiceApi, feedMapperApiToUi: FeedMapperApiToUi): FeedRepository {
        return FeedRepositoryImpl(feedServiceApi, feedMapperApiToUi)
    }

    @Provides
    fun provideFeedServiceApi(): FeedServiceApi {
        return Retrofit.Builder()
            .client(OkHttpClient())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl("https://antonioleiva.com/")
            .build()
            .create(FeedServiceApi::class.java)
    }


}