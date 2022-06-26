package com.livingtechusa.gotjokes

import com.google.gson.annotations.SerializedName


data class Data (

  @SerializedName("memes" ) var memes : ArrayList<Memes> = arrayListOf()

)