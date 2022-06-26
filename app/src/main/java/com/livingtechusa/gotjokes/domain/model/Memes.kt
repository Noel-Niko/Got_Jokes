package com.livingtechusa.gotjokes

import com.google.gson.annotations.SerializedName


data class Memes (

  @SerializedName("id"        ) var id       : String? = null,
  @SerializedName("name"      ) var name     : String? = null,
  @SerializedName("url"       ) var url      : String? = null,
  @SerializedName("width"     ) var width    : Int?    = null,
  @SerializedName("height"    ) var height   : Int?    = null,
  @SerializedName("box_count" ) var boxCount : Int?    = null

)