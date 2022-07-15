package com.livingtechusa.gotjokes.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.json.JSONObject



@JsonClass(generateAdapter = true)
data class Advice(
    @Json(name = "slip")
    val slip: Slip = Slip()
) {
    @JsonClass(generateAdapter = true)
    data class Slip(
        @Json(name = "advice")
        val advice: String = "", // Your smile could make someone's day, don't forget to wear it.
        @Json(name = "id")
        val id: Int = 0 // 23
    ) {
        companion object {
            @JvmStatic
            fun buildFromJson(jsonObject: JSONObject?): Slip? {

                jsonObject?.run {
                    return Slip(
                        optString("advice"),
                        optInt("id")
                    )
                }
                return null
            }
        }
    }
    companion object {
        @JvmStatic
        fun buildFromJson(jsonObject: JSONObject?): Advice? {

            jsonObject?.run {
                return Slip.buildFromJson(optJSONObject("slip"))?.let {
                    Advice(
                        it
                    )
                }
            }
            return null
        }
    }
}