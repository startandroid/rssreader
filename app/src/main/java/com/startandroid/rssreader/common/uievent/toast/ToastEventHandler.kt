package com.startandroid.rssreader.common.uievent.toast

import android.widget.Toast
import androidx.fragment.app.Fragment
import javax.inject.Inject

class ToastEventHandler @Inject constructor(private val fragment: Fragment) {

    fun handle(toastEvent: ToastEvent) {

        if (fragment.activity == null) return

        var text = toastEvent.messageText

        if (text.isNullOrEmpty()) {
            text = toastEvent.messageResId?.let { fragment.requireContext().getString(it) }
        }

        if (!text.isNullOrEmpty()) {
            Toast.makeText(fragment.requireContext(), text, Toast.LENGTH_LONG).show()
        }
    }

}