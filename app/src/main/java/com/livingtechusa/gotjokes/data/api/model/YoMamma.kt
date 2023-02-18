package com.livingtechusa.gotjokes.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.json.JSONObject



@JsonClass(generateAdapter = true)
data class YoMamma(
    @Json(name = "joke")
    val joke: String?  // Yo mamma so fat that when she walked by the TV set I missed 3 seasons of Laguna Beach
) {
    companion object {
        @JvmStatic
        fun buildFromJson(jsonObject: JSONObject?): YoMamma? {

            jsonObject?.run {
                return YoMamma(
                    optString("joke")
                )
            }
            return null
        }
    }
}