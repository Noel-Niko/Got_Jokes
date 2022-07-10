package com.livingtechusa.gotjokes.data.api.model
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Joke(
    image: String = "",
    freeText: String = "",
    yodaText: String = "",
    corpSpeak: String = "",
    randomFact: String = "",
    yoMamma: String = "",
    chuckNorris: String = "",
) {
    var image: String by mutableStateOf(image)
    var freeText: String by mutableStateOf(freeText)
    var yodaText: String by mutableStateOf(yodaText)
    var corpSpeak: String by mutableStateOf(corpSpeak)
    var randomFact: String by mutableStateOf(randomFact)
    var yoMamma: String by mutableStateOf(yoMamma)
    var chuckNorris: String by mutableStateOf(chuckNorris)

}