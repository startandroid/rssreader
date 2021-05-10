package com.startandroid.rssreader.item.preview

import android.view.View
import com.startandroid.domain.dto.Item
import com.startandroid.rssreader.common.state.*
import com.startandroid.rssreader.item.preview.ItemsPreviewUiState
import javax.inject.Inject

class ItemsPreviewUiStateProvider @Inject constructor(): StateFromResult<List<Item>, ItemsPreviewUiState>() {
    override fun map(result: Reslt<List<Item>>): ItemsPreviewUiState {
        return when (result) {
            is Initial -> ItemsPreviewUiState()
            is Loading -> ItemsPreviewUiState(loadingVisibility = View.VISIBLE)
            is Success -> ItemsPreviewUiState(items = result.data)
            is Failure -> ItemsPreviewUiState()
        }
    }
}

