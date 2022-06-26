package com.livingtechusa.gotjokes.domain.model

data class Joke(
    var image: String? = "",
    val freeText: String? = "",
    val yodaText: String? = "",
    val corpSpeak: String? = "",
    val randomFact: String? = "",
    val yoMamma: String? = "",
    val chuckNorris: String? = "",
) {
}