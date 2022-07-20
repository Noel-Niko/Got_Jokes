package com.livingtechusa.gotjokes.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.json.JSONObject



@JsonClass(generateAdapter = true)
data class JokeApi(
    @Json(name = "category")
    val category: String = "", // Dark
    @Json(name = "error")
    val error: Boolean = false, // false
    @Json(name = "flags")
    val flags: Flags = Flags(),
    @Json(name = "id")
    val id: Int = 0, // 165
    @Json(name = "joke")
    val joke: String = "", // My little daughter came to me all excited, saying "Daddy! Daddy! Guess how old I'll be in June!""Oh I don't know princess, why don't you tell me?" I said. She gave me a huge smile and held up four fingers.It's now three hours later, police have joined in and she still won't say where she got them.
    @Json(name = "lang")
    val lang: String = "", // en
    @Json(name = "safe")
    val safe: Boolean = false, // false
    @Json(name = "type")
    val type: String = "" // single
) {
    @JsonClass(generateAdapter = true)
    data class Flags(
        @Json(name = "explicit")
        val explicit: Boolean = false, // false
        @Json(name = "nsfw")
        val nsfw: Boolean = false, // false
        @Json(name = "political")
        val political: Boolean = false, // false
        @Json(name = "racist")
        val racist: Boolean = false, // false
        @Json(name = "religious")
        val religious: Boolean = false, // false
        @Json(name = "sexist")
        val sexist: Boolean = false // false
    ) {
        companion object {
            @JvmStatic
            fun buildFromJson(jsonObject: JSONObject?): Flags? {

                jsonObject?.run {
                    return Flags(
                        optBoolean("explicit"),
                        optBoolean("nsfw"),
                        optBoolean("political"),
                        optBoolean("racist"),
                        optBoolean("religious"),
                        optBoolean("sexist")
                    )
                }
                return null
            }
        }
    }
    companion object {
        @JvmStatic
        fun buildFromJson(jsonObject: JSONObject?): JokeApi? {

            jsonObject?.run {
                return Flags.buildFromJson(optJSONObject("flags"))?.let {
                    JokeApi(
                        optString("category"),
                        optBoolean("error"),
                        it,  //Flags.buildFromJson(optJSONObject("flags")),
                        optInt("id"),
                        optString("joke"),
                        optString("lang"),
                        optBoolean("safe"),
                        optString("type")
                    )
                }
            }
            return null
        }
    }
}