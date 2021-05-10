package com.startandroid.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemDb(
    @PrimaryKey
    val link: String,
    val feedUrl: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val datetime: Long
)