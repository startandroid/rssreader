package com.startandroid.data.repository

import android.util.Log
import android.util.LruCache
import androidx.room.withTransaction
import com.startandroid.data.api.FeedApi
import com.startandroid.data.api.FeedServiceApi
import com.startandroid.data.db.AppDatabase
import com.startandroid.data.mapping.FeedMapperApiToDb
import com.startandroid.data.mapping.FeedMapperApiToUi
import com.startandroid.data.mapping.ItemMapperApiToDb
import com.startandroid.data.mapping.ItemMapperApiToUi
import com.startandroid.domain.dto.Feed
import com.startandroid.domain.dto.Item
import com.startandroid.domain.repository.FeedRepository
import kotlinx.coroutines.flow.Flow

class FeedRepositoryImpl(
    private val feedServiceApi: FeedServiceApi,
    private val feedApiCache: LruCache<String, FeedApi>,
    private val feedMapperApiToUi: FeedMapperApiToUi,
    private val itemMapperApiToUi: ItemMapperApiToUi,
    private val feedMapperApiToDb: FeedMapperApiToDb,
    private val itemMapperApiToDb: ItemMapperApiToDb,
    private val appDatabase: AppDatabase
) : FeedRepository {

    override suspend fun fetchFeed(url: String): Feed {
        val feedApi = fetchOrGetCachedFeed(url)
        return feedMapperApiToUi.map(feedApi)
    }

    override suspend fun fetchItems(url: String): List<Item> {
        val feedApi = fetchOrGetCachedFeed(url)
        return itemMapperApiToUi.mapList(feedApi.channel.itemList)
    }

    override suspend fun refreshItems() {
        Log.d("qweee", "refreshItems")
        val feedList = appDatabase.feedDao().getFeedList()

        feedList.forEach {
            try {
                refreshFeedItems(it.url)
            } catch (e: Exception) {
                e.printStackTrace()
                // TODO update feed status
            }
        }
    }

    private suspend fun refreshFeedItems(url: String) {
        val feed = feedServiceApi.fetchFeed(url)
        val items = itemMapperApiToDb.mapList(feed.channel.itemList)
        appDatabase.itemDao().upsertAll(items)
    }

    override suspend fun addFeedWithItems(url: String) {
        val feedApi = fetchOrGetCachedFeed(url)

        val feedDb = feedMapperApiToDb.map(feedApi)

        val itemsDb = itemMapperApiToDb.mapList(feedApi.channel.itemList).map { it.copy(feedUrl = url) }

        appDatabase.withTransaction {
            appDatabase.feedDao().insert(feedDb)
            appDatabase.itemDao().upsertAll(itemsDb)
        }

    }

    override fun getFeedList(): Flow<List<Feed>> {
        return appDatabase.feedDao().getFeedListFlow()
    }

    override fun getFeedCount(): Flow<Int> {
        return appDatabase.feedDao().getFeedCount()
    }

    override suspend fun clean() {
        appDatabase.withTransaction {
            appDatabase.itemDao().clean()
            appDatabase.feedDao().clean()
        }
    }

    private suspend fun fetchOrGetCachedFeed(url: String): FeedApi {
        return feedApiCache.get(url) ?: feedServiceApi.fetchFeed(url).also {
            it.channel.feedUrl = url
            feedApiCache.put(url, it)
        }
    }

}