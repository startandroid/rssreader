package com.startandroid.rssreader.feed.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startandroid.domain.usecase.GetFeedListUseCase
import com.startandroid.rssreader.common.state.StateProvider
import com.startandroid.rssreader.common.uievent.navigation.NavigationEventProvider
import com.startandroid.rssreader.common.uievent.navigation.NavigationHelper
import com.startandroid.rssreader.feed.add.AddFeedFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedListViewModel @Inject constructor(
    getFeedListUseCase: GetFeedListUseCase,
    private val state: FeedListUiStateProvider,
    private val navigation: NavigationHelper
): ViewModel(),
    StateProvider<FeedListUiState> by state,
    NavigationEventProvider by navigation
{

    init {
        getFeedListUseCase.invoke()
            .onEach { state.success(it) }
            .launchIn(viewModelScope)
    }

    fun onAddClick() {
        navigation.replaceFragment(AddFeedFragment::class)
    }

}