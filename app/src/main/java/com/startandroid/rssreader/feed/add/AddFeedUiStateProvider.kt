package com.startandroid.rssreader.feed.add

import android.view.View
import com.startandroid.domain.dto.Feed
import com.startandroid.rssreader.common.state.*
import javax.inject.Inject

class AddFeedUiStateProvider @Inject constructor(): StateFromResult<Feed, AddFeedUiState>() {
    override fun map(result: Reslt<Feed>): AddFeedUiState {
        return when (result) {
            is Initial -> AddFeedUiState()
            is Loading -> AddFeedUiState(checkVisibility = View.INVISIBLE, loadingVisibility = View.VISIBLE)
            is Success -> result.data.run {  AddFeedUiState(titleText = title, descriptionText = description, imageUrl = imageUrl, cardVisibility = View.VISIBLE) }
            is Failure -> AddFeedUiState()
        }
    }
}

