package com.startandroid.rssreader.common

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun <T> Flow<T>.collectIn(lifecycleOwner: LifecycleOwner, minimalState: Lifecycle.State = Lifecycle.State.INITIALIZED, action: suspend (value:T) -> Unit) {
    lifecycleOwner.lifecycleScope.launch {
        collect {
            if (lifecycleOwner.lifecycle.currentState.isAtLeast(minimalState)) {
                action.invoke(it)
            }
        }

    }
}

fun <T> Flow<T>.collectInWhileStarted(lifecycleOwner: LifecycleOwner, action: suspend (value:T) -> Unit) =
    collectIn(lifecycleOwner, Lifecycle.State.STARTED, action)


inline fun <reified T> Bundle.putKSerializable(key: String, value: T) {
    this.putString(key, Json.encodeToString(value))
}

inline fun <reified T> Bundle.getKSerializable(key: String): T? {
    return this.getString(key)?.let { Json.decodeFromString<T>(it) }
}


