package com.startandroid.rssreader.feed.list

import android.view.View
import com.startandroid.domain.dto.Feed

data class FeedListUiState(
    val feedList: List<Feed> = emptyList(),
    val emptyTextVisibility: Int = View.INVISIBLE
)