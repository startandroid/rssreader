package com.startandroid.rssreader.common.state

sealed class Reslt<out T: Any>
object Initial : Reslt<Nothing>()
object Loading : Reslt<Nothing>()
data class Success<out T: Any>(val data: T): Reslt<T>()
data class Failure(val error: Throwable? = null, val message: String = ""): Reslt<Nothing>()
