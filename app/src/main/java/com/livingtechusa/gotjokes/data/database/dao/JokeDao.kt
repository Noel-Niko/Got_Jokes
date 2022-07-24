package com.livingtechusa.gotjokes.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.livingtechusa.gotjokes.data.database.entity.JokeEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface JokeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJoke(GoogleImage: JokeEntity)

    @Query("DELETE FROM JokeEntity WHERE imageUrl = :imageUrl AND caption = :caption")
    suspend fun removeJoke(imageUrl: String, caption: String)

    @Query("SELECT * FROM JokeEntity ORDER BY dateAdded DESC")
    fun getAllFromJokeTable(): Flow<List<JokeEntity>>

    @Query("SELECT * FROM JokeEntity LIMIT 1")
    suspend fun getOneFromJokeTable(): JokeEntity

    @Query("DELETE FROM JokeEntity ")
    suspend fun clearJokeTable()

}