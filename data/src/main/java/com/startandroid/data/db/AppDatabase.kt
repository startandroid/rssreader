package com.startandroid.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FeedDb::class, ItemDb::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun feedDao(): FeedDao
    abstract fun itemDao(): ItemDao
}