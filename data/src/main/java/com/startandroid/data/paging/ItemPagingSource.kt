package com.startandroid.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.startandroid.data.db.FeedDao
import com.startandroid.data.db.ItemDao
import com.startandroid.data.db.ItemDb
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class ItemPagingSource @Inject constructor (private val itemDao: ItemDao, private val feedDao: FeedDao): PagingSource<Long, ItemDb>() {

    init {
        MainScope().launch {
            feedDao.getFeedCount()
                .distinctUntilChanged()
                .drop(1)
                .collect {
                    invalidate()
                    cancel()
                }
        }

    }

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, ItemDb> {
        val startDatetime = params.key ?: Date().time
        val data = itemDao.getLimitedAfterDatetime(startDatetime, params.loadSize)
        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = data.lastOrNull()?.datetime
        )
    }


    override fun getRefreshKey(state: PagingState<Long, ItemDb>): Long? {
        return null
    }

}