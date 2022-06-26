package com.livingtechusa.gotjokes.network.responses

import com.google.gson.annotations.SerializedName
import com.livingtechusa.gotjokes.Data

data class ImgFlipResponse(
    @SerializedName("success" ) var success : Boolean? = null,
    @SerializedName("data"    ) var data    : Data    = Data()


) {
}