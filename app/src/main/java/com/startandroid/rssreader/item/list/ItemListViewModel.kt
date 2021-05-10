package com.startandroid.rssreader.item.list

import androidx.lifecycle.ViewModel
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
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(
    private val itemDao: ItemDao,
    private val feedDao: FeedDao,
    private val itemMapperDbToUi: ItemMapperDbToUi,
    private val navigation: NavigationHelper
) : ViewModel(), NavigationEventProvider by navigation {


    val pagingDataFlow = Pager(config = PagingConfig(pageSize = 10, initialLoadSize = 10)) {
        ItemPagingSource(itemDao, feedDao)
    }.flow
        .map { pagingData ->
            pagingData.map { itemDb ->
                itemMapperDbToUi.map(itemDb)
            }
        }

    fun onFeedsClick() {
        navigation.replaceFragment(FeedListFragment::class)
    }


}