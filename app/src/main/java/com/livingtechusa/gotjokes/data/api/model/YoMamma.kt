package com.livingtechusa.gotjokes.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.json.JSONObject



@JsonClass(generateAdapter = true)
data class YoMamma(
    @Json(name = "joke")
    val joke: String?  // Yo momma is so skinny that instead of calling her your parent, you call her transparent.
) {
//    companion object {
//        @JvmStatic
//        fun buildFromJson(jsonObject: JSONObject?): YoMamma? {
//
//            jsonObject?.run {
//                return YoMamma(
//                    optString("joke")
//                )
//            }
//            return null
//        }
//    }
}