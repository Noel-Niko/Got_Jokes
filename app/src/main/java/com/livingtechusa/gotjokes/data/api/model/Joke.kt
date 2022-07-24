package com.livingtechusa.gotjokes.data.api.model
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.Date

class Joke(
    id: Long = 0L,
    imageUrl: String = "",
    caption: String = "",
    dateAdded: Date,
) {
    var id: Long by  mutableStateOf(id)
    var imageUrl: String by mutableStateOf(imageUrl)
    var caption: String by mutableStateOf(caption)
    var dateAdded: Date by mutableStateOf(dateAdded)

}