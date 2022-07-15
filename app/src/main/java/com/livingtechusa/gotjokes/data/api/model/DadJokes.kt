package com.livingtechusa.gotjokes.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.json.JSONObject



@JsonClass(generateAdapter = true)
data class DadJokes(
    @Json(name = "attachments")
    val attachments: List<Attachment> = listOf(),
    @Json(name = "response_type")
    val responseType: String = "", // in_channel
    @Json(name = "username")
    val username: String = "" // icanhazdadjoke
) {
    @JsonClass(generateAdapter = true)
    data class Attachment(
        @Json(name = "fallback")
        val fallback: String = "", // I needed a password eight characters long so I picked Snow White and the Seven Dwarfs.
        @Json(name = "footer")
        val footer: String = "", // <https://icanhazdadjoke.com/j/W8MClVvXSf|permalink> - <https://icanhazdadjoke.com|icanhazdadjoke.com>
        @Json(name = "text")
        val text: String = "" // I needed a password eight characters long so I picked Snow White and the Seven Dwarfs.
    ) {
        companion object {
            @JvmStatic
            fun buildFromJson(jsonObject: JSONObject?): Attachment? {

                jsonObject?.run {
                    return Attachment(
                        optString("fallback"),
                        optString("footer"),
                        optString("text")
                    )
                }
                return null
            }
        }
    }
    companion object {
        @JvmStatic
        fun buildFromJson(jsonObject: JSONObject?): DadJokes? {

            jsonObject?.run {
                return DadJokes(
                    ArrayList<Attachment>().apply {
                        optJSONArray("attachments")?.let {
                            for(i in 0 until it.length()) {
                                Attachment.buildFromJson(it.getJSONObject(i))?.let { it1 -> this.add(it1) }
                            }
                        }
                    },
                    optString("response_type"),
                    optString("username")
                )
            }
            return null
        }
    }
}