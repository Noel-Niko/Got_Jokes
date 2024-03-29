package com.livingtechusa.gotjokes.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "ImageSearchEntity")
data class ImageSearchEntity (
    @PrimaryKey
    var imageUrl: String = "",
    var downLoadedDate: Date
    )