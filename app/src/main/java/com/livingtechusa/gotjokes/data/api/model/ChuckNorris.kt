package com.livingtechusa.gotjokes.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.json.JSONObject



@JsonClass(generateAdapter = true)
data class ChuckNorris(
    @Json(name = "categories")
    val categories: List<String?> = emptyList(),
    @Json(name = "created_at")
    val createdAt: String = "", // 2020-01-05 13:42:30.177068
    @Json(name = "icon_url")
    val iconUrl: String = "", // https://assets.chucknorris.host/img/avatar/chucknorris.png
    @Json(name = "id")
    val id: String = "", // gfsBRb5kTrqnmStxaoWFQW
    @Json(name = "updated_at")
    val updatedAt: String = "", // 2020-01-5
    @Json(name = "url")
    val url: String = "", // https://api.cuck.io
    @Json(name = "value")
    val value: String = "" // Chuck Norris created giraffes when he uppercut a horse.
) {
    companion object {
        @JvmStatic
        fun buildFromJson(jsonObject: JSONObject?): ChuckNorris? {

            jsonObject?.run {
                return ChuckNorris(
                    ArrayList<String>().apply {
                        optJSONArray("categories")?.let {
                            for(i in 0 until it.length()) {
                                this.add((it.getJSONObject(i).toString()))
                            }
                        }
                    },
                    optString("created_at"),
                    optString("icon_url"),
                    optString("id"),
                    optString("updated_at"),
                    optString("url"),
                    optString("value")
                )
            }
            return null
        }
    }
}