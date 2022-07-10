package com.livingtechusa.gotjokes.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.json.JSONObject



@JsonClass(generateAdapter = true)
data class YodaSpeak(
    @Json(name = "contents")
    val contents: Contents,
    @Json(name = "success")
    val success: Success
) {
    @JsonClass(generateAdapter = true)
    data class Contents(
        @Json(name = "text")
        val text: String, // Master Obiwan has lost a planet.
        @Json(name = "translated")
        val translated: String, // Lost a planet,  master obiwan has.
        @Json(name = "translation")
        val translation: String // yoda
    ) {
        companion object {
            @JvmStatic
            fun buildFromJson(jsonObject: JSONObject?): Contents? {

                jsonObject?.run {
                    return Contents(
                        optString("text"),
                        optString("translated"),
                        optString("translation")
                    )
                }
                return null
            }
        }
    }

    @JsonClass(generateAdapter = true)
    data class Success(
        @Json(name = "total")
        val total: Int // 1
    ) {
        companion object {
            @JvmStatic
            fun buildFromJson(jsonObject: JSONObject?): Success? {

                jsonObject?.run {
                    return Success(
                        optInt("total")
                    )
                }
                return null
            }
        }
    }
    companion object {
        @JvmStatic
        fun buildFromJson(jsonObject: JSONObject?): YodaSpeak? {

            jsonObject?.run {
                return Contents.buildFromJson(optJSONObject("contents"))?.let {
                    Success.buildFromJson(optJSONObject("success"))?.let { it1 ->
                        YodaSpeak(
                            it,
                            it1
                        )
                    }
                }
            }
            return null
        }
    }
}