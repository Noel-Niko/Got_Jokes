package com.livingtechusa.gotjokes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.livingtechusa.gotjokes.data.database.dao.ImageSearchDao
import com.livingtechusa.gotjokes.data.database.dao.JokeDao
import com.livingtechusa.gotjokes.data.database.entity.ImageSearchEntity
import com.livingtechusa.gotjokes.data.database.entity.JokeEntity


@Database(
    entities =
    [ImageSearchEntity::class, JokeEntity::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(Converters::class)

abstract class AppDatabase : RoomDatabase() {
    abstract fun imageSearchDao(): ImageSearchDao
    abstract fun jokeDao(): JokeDao


}
