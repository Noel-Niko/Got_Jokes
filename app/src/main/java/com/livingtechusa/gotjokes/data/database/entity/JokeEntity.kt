package com.livingtechusa.gotjokes.data.database.entity

import android.graphics.Bitmap
import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.URI
import java.util.Date

@Entity(tableName = "JokeEntity", primaryKeys = ["imageUrl","caption"])
data class JokeEntity (
    var imageUrl: String = "",
    var caption: String = "",
    var dateAdded: Date = Date(System.currentTimeMillis()),
    var imgURI: Uri? = null
    ) {
}