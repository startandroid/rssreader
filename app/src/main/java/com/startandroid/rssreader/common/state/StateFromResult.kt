package com.startandroid.rssreader.common.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

abstract class StateFromResult<T: Any, S>(): StateProvider<S> {

    private val resultFlow = MutableStateFlow<Reslt<T>>(Initial)
    private val state = resultFlow.map { map(it) }.asLiveData()

    override fun state() = state
    abstract fun map(result: Reslt<T>): S

    fun initial() = postResult(Initial)
    fun loading() = postResult(Loading)
    fun success(data: T) = postResult(Success(data))
    fun failure(error: Throwable? = null, message: String = "") = postResult(Failure(error, message))

    private fun postResult(result: Reslt<T>) {
        resultFlow.value = result
    }

}

interface StateProvider<S> {
    fun state(): LiveData<S>
}