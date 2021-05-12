package com.startandroid.rssreader.item.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.startandroid.data.db.FeedDao
import com.startandroid.data.db.ItemDao
import com.startandroid.data.mapping.ItemMapperDbToUi
import com.startandroid.data.paging.ItemPagingSource
import com.startandroid.rssreader.common.uievent.navigation.NavigationEventProvider
import com.startandroid.rssreader.common.uievent.navigation.NavigationHelper
import com.startandroid.rssreader.feed.add.AddFeedFragment
import com.startandroid.rssreader.feed.list.FeedListFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(
    private val itemDao: ItemDao,
    private val feedDao: FeedDao,
    private val itemMapperDbToUi: ItemMapperDbToUi,
    private val navigation: NavigationHelper
) : ViewModel(), NavigationEventProvider by navigation {

    init {
        viewModelScope.launch {
            feedDao.getFeedCount()
                .distinctUntilChanged()
                .drop(1)
                .collect {
                    pagingSource?.invalidate()
                }
        }
    }

    private var pagingSource: ItemPagingSource? = null

    val pagingDataFlow = Pager(config = PagingConfig(pageSize = 10, initialLoadSize = 10)) {
        createPagingSource()
    }.flow
        .map { pagingData ->
            pagingData.map { itemDb ->
                itemMapperDbToUi.map(itemDb)
            }
        }

    private fun createPagingSource(): ItemPagingSource {
        return ItemPagingSource(itemDao).also { pagingSource = it }
    }

    fun onFeedsClick() {
        navigation.replaceFragment(FeedListFragment::class)
    }

    fun onRefreshSwiped() {
        pagingSource?.invalidate()
    }


}