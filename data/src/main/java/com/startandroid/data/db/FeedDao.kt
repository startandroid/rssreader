package com.startandroid.data.db

import androidx.room.*
import com.startandroid.domain.dto.Feed
import kotlinx.coroutines.flow.Flow

@Dao
interface FeedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(feed: FeedDb)

    @Query("SELECT * FROM feeds")
    fun getFeedListFlow(): Flow<List<Feed>>

    @Query("SELECT * FROM feeds")
    fun getFeedList(): List<Feed>

    @Query("SELECT COUNT(*) FROM feeds")
    fun getFeedCount(): Flow<Int>

    @Query("DELETE FROM feeds")
    suspend fun clean()

}