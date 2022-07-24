package com.livingtechusa.gotjokes.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.livingtechusa.gotjokes.data.database.entity.ImageSearchEntity
import java.util.Date

@Dao
interface ImageSearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(image: ImageSearchEntity)

    @Query("DELETE FROM ImageSearchEntity WHERE imageUrl =:imageUrl")
    suspend fun removeImage(imageUrl: String)

    @Query("SELECT * FROM ImageSearchEntity LIMIT 1")
    suspend fun getOneFromImageSearchTable(): ImageSearchEntity

    @Query("SELECT * FROM ImageSearchEntity")
    suspend fun getAllFromImageSearchTable(): List<ImageSearchEntity>

    @Query("DELETE FROM ImageSearchEntity WHERE downLoadedDate <= :date")
    suspend fun clearImageSearchTable(date: Date)
}