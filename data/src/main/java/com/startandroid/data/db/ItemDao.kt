package com.startandroid.data.db

import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ItemDb>)

    @Query("SELECT * FROM items ORDER BY datetime DESC")
    fun pagingSource(): PagingSource<Int, ItemDb>

    @Query("DELETE FROM items WHERE link = :link")
    suspend fun delete(link: String)

    @Query("SELECT * FROM items WHERE datetime < :datetime ORDER BY datetime DESC LIMIT :limit")
    suspend fun getLimitedAfterDatetime(datetime: Long, limit: Int): List<ItemDb>

    @Query("DELETE FROM items")
    suspend fun clean()

}