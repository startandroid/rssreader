package com.startandroid.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feeds")
data class FeedDb(
    @PrimaryKey
    val url: String,
    val title: String,
    val description: String,
    var imageUrl: String = "",
)

