package com.startandroid.rssreader.item.preview

import android.view.View
import com.startandroid.domain.dto.Item

data class ItemsPreviewUiState(
    val items: List<Item> = emptyList(),
    val loadingVisibility: Int = View.INVISIBLE
)