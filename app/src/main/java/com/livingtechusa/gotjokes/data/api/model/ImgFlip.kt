package com.livingtechusa.gotjokes.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.json.JSONObject



@JsonClass(generateAdapter = true)
data class ImgFlip(
    @Json(name = "data")
    val `data`: Data,
    @Json(name = "success")
    val success: Boolean // true
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "memes")
        val memes: List<Meme>
    ) {
        @JsonClass(generateAdapter = true)
        data class Meme(
            @Json(name = "box_count")
            val boxCount: Int, // 2
            @Json(name = "height")
            val height: Int, // 1200
            @Json(name = "id")
            val id: String, // 181913649
            @Json(name = "name")
            val name: String, // Drake Hotline Bling
            @Json(name = "url")
            val url: String, // https://i.imgflip.com/30b1gx.jpg
            @Json(name = "width")
            val width: Int // 1200
        ) {
            companion object {
                @JvmStatic
                fun buildFromJson(jsonObject: JSONObject?): Meme? {

                    jsonObject?.run {
                        return Meme(
                            optInt("box_count"),
                            optInt("height"),
                            optString("id"),
                            optString("name"),
                            optString("url"),
                            optInt("width")
                        )
                    }
                    return null
                }
            }
        }
        companion object {
            @JvmStatic
            fun buildFromJson(jsonObject: JSONObject?): Data? {

                jsonObject?.run {
                    return Data(
                        ArrayList<Meme>().apply {
                            optJSONArray("memes")?.let {
                                for(i in 0 until it.length()) {
                                    Meme.buildFromJson(it.getJSONObject(i))?.let { it1 -> this.add(it1) }
                                }
                            }
                        }
                    )
                }
                return null
            }
        }
    }
    companion object {
        @JvmStatic
        fun buildFromJson(jsonObject: JSONObject?): ImgFlip? {

            jsonObject?.run {
                return Data.buildFromJson(optJSONObject("data"))?.let {
                    ImgFlip(
                        it,
                        optBoolean("success")
                    )
                }
            }
            return null
        }
    }
}