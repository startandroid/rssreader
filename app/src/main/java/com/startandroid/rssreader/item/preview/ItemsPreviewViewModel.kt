package com.startandroid.rssreader.item.preview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startandroid.domain.usecase.FetchItemsUseCase
import com.startandroid.rssreader.common.state.StateProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsPreviewViewModel @Inject constructor(
    private val fetchItemsUseCase: FetchItemsUseCase,
    private val state: ItemsPreviewUiStateProvider
) : ViewModel(),
    StateProvider<ItemsPreviewUiState> by state
{

    private var url: String = ""

    fun fetch(feedUrl: String?) {
        if (feedUrl.isNullOrEmpty()) return
        if (url.isNotEmpty()) return

        url = feedUrl
        state.loading()
        viewModelScope.launch {
            try {
                val feed = fetchItemsUseCase.invoke(url)
                state.success(feed)
            } catch (e: Exception) {
                e.printStackTrace()
                state.failure(e)
            }
        }
    }
}


