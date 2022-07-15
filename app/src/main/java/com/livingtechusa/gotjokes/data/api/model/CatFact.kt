package com.livingtechusa.gotjokes.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.json.JSONObject



@JsonClass(generateAdapter = true)
data class CatFact(
    @Json(name = "fact")
    val fact: String = "", // Cat bites are more likely to become infected than dog bites.
    @Json(name = "length")
    val length: Int = 0 // 60
) {
    companion object {
        @JvmStatic
        fun buildFromJson(jsonObject: JSONObject?): CatFact? {

            jsonObject?.run {
                return CatFact(
                    optString("fact"),
                    optInt("length")
                )
            }
            return null
        }
    }
}