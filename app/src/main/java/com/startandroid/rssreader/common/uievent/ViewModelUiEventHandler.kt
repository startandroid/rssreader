package com.startandroid.rssreader.common.uievent

import androidx.fragment.app.Fragment
import com.startandroid.rssreader.common.uievent.toast.ToastEventHandler
import com.startandroid.rssreader.common.collectInWhileStarted
import com.startandroid.rssreader.common.uievent.navigation.NavigationEventHandler
import com.startandroid.rssreader.common.uievent.navigation.NavigationEventProvider
import com.startandroid.rssreader.common.uievent.toast.ToastEventProvider
import javax.inject.Inject

class ViewModelUiEventHandler @Inject constructor(
    private val fragment: Fragment,
    private val toastEventHandler: dagger.Lazy<ToastEventHandler>,
    private val navigationEventHandler: dagger.Lazy<NavigationEventHandler>
) {

    fun collectEvents(provider: Any) {
        if (provider is ToastEventProvider) {
            provider.toastEvents().collectInWhileStarted(fragment.viewLifecycleOwner) {
                toastEventHandler.get().handle(it)
            }
        }
        if (provider is NavigationEventProvider) {
            provider.navigationEvents().collectInWhileStarted(fragment.viewLifecycleOwner) {
                navigationEventHandler.get().handle(it)
            }
        }
    }

}