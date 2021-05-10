package com.startandroid.rssreader.common.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

abstract class StateFromResult<T: Any, S>: StateProvider<S> {

    private val resultFlow = MutableStateFlow<Reslt<T>>(Initial)
    private val state = resultFlow.map { map(it) }.asLiveData()

    override fun state() = state
    abstract fun map(result: Reslt<T>): S

    fun initial() = post(Initial)
    fun loading() = post(Loading)
    fun success(data: T) = post(Success(data))
    fun failure(error: Throwable? = null, message: String = "") = post(Failure(error, message))

    private fun post(result: Reslt<T>) {
        resultFlow.value = result
    }

}

interface StateProvider<S> {
    fun state(): LiveData<S>
}