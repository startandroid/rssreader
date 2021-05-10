package com.startandroid.data.mapping

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class TimeFormatter @Inject constructor(private val simpleDateFormatList: MutableList<SimpleDateFormat>) {

    fun parseItemPubDate(date: String): Long {
        if (date.isEmpty()) return Date().time

        val parsedDate = simpleDateFormatList.mapNotNull {
            try {
                it.parse(date)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }.firstOrNull() ?: Date()

        return parsedDate.time
    }
}