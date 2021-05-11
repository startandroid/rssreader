package com.startandroid.data.di

import android.app.Application
import android.util.LruCache
import androidx.room.Room
import com.startandroid.data.api.FeedApi
import com.startandroid.data.api.FeedServiceApi
import com.startandroid.data.db.AppDatabase
import com.startandroid.data.db.FeedDao
import com.startandroid.data.db.ItemDao
import com.startandroid.data.mapping.*
import com.startandroid.data.repository.FeedRepositoryImpl
import com.startandroid.domain.repository.FeedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideFeedRepository(
        feedServiceApi: FeedServiceApi,
        feedMapperApiToUi: FeedMapperApiToUi,
        itemMapperApiToUi: ItemMapperApiToUi,
        feedMapperApiToDb: FeedMapperApiToDb,
        itemMapperApiToDb: ItemMapperApiToDb,
        appDatabase: AppDatabase,
        cache: LruCache<String, FeedApi>
    ): FeedRepository {
        return FeedRepositoryImpl(feedServiceApi, cache, feedMapperApiToUi, itemMapperApiToUi,  feedMapperApiToDb, itemMapperApiToDb,  appDatabase)
    }

    @Singleton
    @Provides
    fun provideFeedServiceApi(): FeedServiceApi {
        return Retrofit.Builder()
            .client(OkHttpClient())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .baseUrl("https://dummy.com/")
            .build()
            .create(FeedServiceApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "database").build()
    }

    @Provides
    fun provideFeedDao(database: AppDatabase): FeedDao {
        return database.feedDao()
    }

    @Provides
    fun provideItemDao(database: AppDatabase): ItemDao {
        return database.itemDao()
    }

    @Provides
    fun provideFeedApiLruCache(): LruCache<String, FeedApi> {
        return LruCache(5)
    }

    @Provides
    fun provideParseDateTimeFormatterList(): MutableList<SimpleDateFormat> {
        return mutableListOf(
            SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.getDefault()),
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        )
    }

    @Provides
    fun provideDateTimeFormatter(): SimpleDateFormat {
        return SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault())
    }



}