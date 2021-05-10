package com.startandroid.rssreader.feed.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startandroid.domain.dto.Feed
import com.startandroid.domain.usecase.AddFeedUseCase
import com.startandroid.domain.usecase.FetchFeedUseCase
import com.startandroid.rssreader.common.*
import com.startandroid.rssreader.common.state.StateProvider
import com.startandroid.rssreader.common.uievent.navigation.NavigationEventProvider
import com.startandroid.rssreader.common.uievent.navigation.NavigationHelper
import com.startandroid.rssreader.common.uievent.toast.ToastEventProvider
import com.startandroid.rssreader.item.preview.ItemsPreviewFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddFeedViewModel @Inject constructor(
    private val fetchFeedUseCase: FetchFeedUseCase,
    private val addFeedUseCase: AddFeedUseCase,
    private val state: AddFeedUiStateProvider,
    private val toast: ToastHelper,
    private val navigation: NavigationHelper
) : ViewModel(),
    StateProvider<AddFeedUiState> by state,
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
        navigation.replaceFragment(ItemsPreviewFragment::class, ItemsPreviewFragment.feedArgs(lastFeed?.url))
    }

    fun onAddClick() {
        viewModelScope.launch {
            lastFeed?.url?.let { addFeedUseCase.invoke(it) }
            navigation.back()
        }
    }

}


