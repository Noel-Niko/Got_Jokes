package com.livingtechusa.gotjokes.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.livingtechusa.gotjokes.data.database.entity.JokeEntity


@Dao
interface JokeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJoke(GoogleImage: JokeEntity)

    @Query("DELETE FROM JokeEntity WHERE id =:id")
    suspend fun removeJoke(id: Long)

    @Query("SELECT * FROM JokeEntity ORDER BY dateAdded DESC")
    suspend fun getAllFromJokeTable(): List<JokeEntity>

    @Query("SELECT * FROM JokeEntity LIMIT 1")
    suspend fun getOneFromJokeTable(): JokeEntity

    @Query("DELETE FROM JokeEntity ")
    suspend fun clearJokeTable()

}