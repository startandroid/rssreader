package com.startandroid.rssreader.feed

import android.view.View
import com.startandroid.domain.dto.Feed
import com.startandroid.rssreader.common.state.*
import javax.inject.Inject

class FeedStateFromResult @Inject constructor(): StateFromResult<Feed, FeedViewModelState>() {
    override fun map(result: Reslt<Feed>): FeedViewModelState {
        return when (result) {
            is Initial -> FeedViewModelState()
            is Loading -> FeedViewModelState(checkVisibility = View.INVISIBLE, loadingVisibility = View.VISIBLE)
            is Success -> result.data.run {  FeedViewModelState(titleText = title, descriptionText = description, imageUrl = imageUrl, cardVisibility = View.VISIBLE) }
            is Failure -> FeedViewModelState()
        }
    }
}

