package com.livingtechusa.gotjokes.network.model

import com.google.gson.annotations.SerializedName

data class Meme(
    @SerializedName("url")
    var url: String? = null,

    @SerializedName("width")
    var width: Int? = null,

    @SerializedName("height")
    var height: Int? = null,

) {
}