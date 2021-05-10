package com.startandroid.rssreader.feed.list

import android.view.View
import com.startandroid.domain.dto.Feed
import com.startandroid.rssreader.common.state.*
import javax.inject.Inject

class FeedListUiStateProvider @Inject constructor(): StateFromResult<List<Feed>, FeedListUiState>() {
    override fun map(result: Reslt<List<Feed>>): FeedListUiState {
        return when (result) {
            is Initial -> FeedListUiState()
            is Loading -> FeedListUiState()
            is Success -> result.data.let {
                if (it.isEmpty()) FeedListUiState(emptyTextVisibility = View.VISIBLE) else FeedListUiState(feedList = it)
            }
            is Failure -> FeedListUiState()
        }
    }
}

