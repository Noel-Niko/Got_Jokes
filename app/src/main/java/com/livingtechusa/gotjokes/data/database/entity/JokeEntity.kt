package com.livingtechusa.gotjokes.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "JokeEntity")
data class JokeEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var imageUrl: String = "",
    var caption: String = "",
    var dateAdded: Date
    ) {
}