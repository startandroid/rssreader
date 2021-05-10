package com.startandroid.rssreader.feed.add

import android.view.View

data class AddFeedUiState(
    val titleText: String = "",
    val descriptionText: String = "",
    val imageUrl: String = "",
    val loadingVisibility: Int = View.INVISIBLE,
    val checkVisibility: Int = View.VISIBLE,
    val cardVisibility: Int = View.INVISIBLE,
)