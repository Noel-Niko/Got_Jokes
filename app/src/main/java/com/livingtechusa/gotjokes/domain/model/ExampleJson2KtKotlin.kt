package com.livingtechusa.gotjokes

import com.google.gson.annotations.SerializedName


data class ExampleJson2KtKotlin (

  @SerializedName("success" ) var success : Boolean? = null,
  @SerializedName("data"    ) var data    : Data?    = Data()

)