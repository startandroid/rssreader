package com.startandroid.rssreader.common.uievent.navigation

import androidx.fragment.app.Fragment
import com.startandroid.rssreader.R
import javax.inject.Inject

class NavigationEventHandler @Inject constructor(
    val fragment: Fragment
) {

    fun handle(event: NavigationEvent) {
        when (event) {
            is NavigationFragmentEvent -> handleFragment(event)
            Back -> handleBack()
        }
    }

    private fun handleFragment(event: NavigationFragmentEvent) {
        if (!fragment.isAdded) return
        val newFragment = event.kls.constructors.first { it.parameters.isEmpty() }.call()
        event.arguments?.let { newFragment.arguments = it }
        fragment.parentFragmentManager
            .beginTransaction()
            .apply {
                if (event.action == NavigationFragmentAction.ADD)
                    add(R.id.container, newFragment)
                else {
                    replace(R.id.container, newFragment)
                }
            }.addToBackStack(event.kls.simpleName)
            .commit()
    }

    private fun handleBack() {
        fragment.activity?.onBackPressed()
    }
}