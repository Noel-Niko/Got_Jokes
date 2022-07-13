package com.livingtechusa.gotjokes.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.json.JSONObject



@JsonClass(generateAdapter = true)
data class RandomFact(
    @Json(name = "id")
    val id: String = "", // 3d561499-ea4a-4bf5-b64e-7798647fa123
    @Json(name = "language")
    val language: String = "", // en
    @Json(name = "permalink")
    val permalink: String = "", // https://uselessfacts.jsph.pl/3d561499-ea4a-4bf5-b64e-7798647fa123
    @Json(name = "source")
    val source: String = "", // djtech.net
    @Json(name = "source_url")
    val sourceUrl: String = "", // http://www.djtech.net/humor/useless_facts.htm
    @Json(name = "text")
    val text: String = "Sorry, you already know it all." // There are 293 ways to make change for a dollar.
) {
    companion object {
        @JvmStatic
        fun buildFromJson(jsonObject: JSONObject?): RandomFact? {

            jsonObject?.run {
                return RandomFact(
                    optString("id"),
                    optString("language"),
                    optString("permalink"),
                    optString("source"),
                    optString("source_url"),
                    optString("text")
                )
            }
            return null
        }
    }
}