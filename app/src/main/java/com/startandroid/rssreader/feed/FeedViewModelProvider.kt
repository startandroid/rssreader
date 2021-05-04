package com.startandroid.rssreader.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.startandroid.domain.usecase.FetchFeedUseCase
import com.startandroid.rssreader.common.ToastHelper
import com.startandroid.rssreader.common.uievent.navigation.NavigationHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedViewModelProvider @Inject constructor(
    private val fetchFeedUseCase: FetchFeedUseCase,
    private val feedStateFromResult: FeedStateFromResult,
    private val toastHelper: ToastHelper,
    private val navigationHelper: NavigationHelper
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedViewModel::class.java)) {
            return FeedViewModel(fetchFeedUseCase, feedStateFromResult, toastHelper, navigationHelper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}