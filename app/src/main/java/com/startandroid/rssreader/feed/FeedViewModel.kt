package com.startandroid.rssreader.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startandroid.domain.dto.Feed
import com.startandroid.domain.usecase.FetchFeedUseCase
import com.startandroid.rssreader.common.*
import com.startandroid.rssreader.common.state.StateProvider
import com.startandroid.rssreader.common.uievent.navigation.NavigationEventProvider
import com.startandroid.rssreader.common.uievent.navigation.NavigationHelper
import com.startandroid.rssreader.common.uievent.toast.ToastEventProvider
import com.startandroid.rssreader.feed.item.ItemListPreviewFragment
import kotlinx.coroutines.launch

class FeedViewModel(
    private val fetchFeedUseCase: FetchFeedUseCase,
    private val state: FeedStateFromResult,
    private val toast: ToastHelper,
    private val navigation: NavigationHelper
) : ViewModel(),
    StateProvider<FeedViewModelState> by state,
    ToastEventProvider by toast,
    NavigationEventProvider by navigation
{
    var lastFeed: Feed? = null

    fun onCheckClick(url: String?) {
        if (url.isNullOrEmpty()) return

        state.loading()
        viewModelScope.launch {
            try {
                val feed = fetchFeedUseCase.invoke(url)
                lastFeed = feed
                state.success(feed)
            } catch (e: Exception) {
                e.printStackTrace()
                state.failure(e)
                toast.show("Error: ${e.message}")
            }
        }
    }

    fun onPreviewClick() {
        navigation.replaceFragment(ItemListPreviewFragment::class, ItemListPreviewFragment.createArguments(lastFeed?.itemList))
    }

}


