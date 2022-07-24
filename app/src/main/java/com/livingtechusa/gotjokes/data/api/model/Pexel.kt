package com.livingtechusa.gotjokes.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.json.JSONObject



@JsonClass(generateAdapter = true)
data class Pexel(
    @Json(name = "next_page")
    val nextPage: String = "", // https://api.pexels.com/v1/curated/?page=4&per_page=80
    @Json(name = "page")
    val page: Int = 0, // 3
    @Json(name = "per_page")
    val perPage: Int = 0, // 80
    @Json(name = "photos")
    val photos: List<Photo> = listOf(),
    @Json(name = "prev_page")
    val prevPage: String = "", // https://api.pexels.com/v1/curated/?page=2&per_page=80
    @Json(name = "total_results")
    val totalResults: Int = 0 // 8000
) {
    @JsonClass(generateAdapter = true)
    data class Photo(
        @Json(name = "alt")
        val alt: String = "", // Sea and Islands Landscape at Dawn
        @Json(name = "avg_color")
        val avgColor: String = "", // #184C74
        @Json(name = "height")
        val height: Int = 0, // 6000
        @Json(name = "id")
        val id: Int = 0, // 12319913
        @Json(name = "liked")
        val liked: Boolean = false, // false
        @Json(name = "photographer")
        val photographer: String = "", // Jhovani  Morales
        @Json(name = "photographer_id")
        val photographerId: Int = 0, // 242490132
        @Json(name = "photographer_url")
        val photographerUrl: String = "", // https://www.pexels.com/@jhovani-morales-242490132
        @Json(name = "src")
        val src: Src = Src(),
        @Json(name = "url")
        val url: String = "", // https://www.pexels.com/photo/sea-and-islands-landscape-at-dawn-12319913/
        @Json(name = "width")
        val width: Int = 0 // 4000
    ) {
        @JsonClass(generateAdapter = true)
        data class Src(
            @Json(name = "landscape")
            val landscape: String = "", // https://images.pexels.com/photos/12319913/pexels-photo-12319913.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=627&w=1200
            @Json(name = "large")
            val large: String = "", // https://images.pexels.com/photos/12319913/pexels-photo-12319913.jpeg?auto=compress&cs=tinysrgb&h=650&w=940
            @Json(name = "large2x")
            val large2x: String = "", // https://images.pexels.com/photos/12319913/pexels-photo-12319913.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940
            @Json(name = "medium")
            val medium: String = "", // https://images.pexels.com/photos/12319913/pexels-photo-12319913.jpeg?auto=compress&cs=tinysrgb&h=350
            @Json(name = "original")
            val original: String = "", // https://images.pexels.com/photos/12319913/pexels-photo-12319913.jpeg
            @Json(name = "portrait")
            val portrait: String = "", // https://images.pexels.com/photos/12319913/pexels-photo-12319913.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800
            @Json(name = "small")
            val small: String = "", // https://images.pexels.com/photos/12319913/pexels-photo-12319913.jpeg?auto=compress&cs=tinysrgb&h=130
            @Json(name = "tiny")
            val tiny: String = "" // https://images.pexels.com/photos/12319913/pexels-photo-12319913.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=200&w=280
        ) {
            companion object {
                @JvmStatic
                fun buildFromJson(jsonObject: JSONObject?): Src? {

                    jsonObject?.run {
                        return Src(
                            optString("landscape"),
                            optString("large"),
                            optString("large2x"),
                            optString("medium"),
                            optString("original"),
                            optString("portrait"),
                            optString("small"),
                            optString("tiny")
                        )
                    }
                    return null
                }
            }
        }
        companion object {
            @JvmStatic
            fun buildFromJson(jsonObject: JSONObject?): Photo? {

                jsonObject?.run {
                    return Src.buildFromJson(optJSONObject("src"))?.let {
                        Photo(
                            optString("alt"),
                            optString("avg_color"),
                            optInt("height"),
                            optInt("id"),
                            optBoolean("liked"),
                            optString("photographer"),
                            optInt("photographer_id"),
                            optString("photographer_url"),
                            it,
                            optString("url"),
                            optInt("width")
                        )
                    }
                }
                return null
            }
        }
    }
    companion object {
        @JvmStatic
        fun buildFromJson(jsonObject: JSONObject?): Pexel? {

            jsonObject?.run {
                return Pexel(
                    optString("next_page"),
                    optInt("page"),
                    optInt("per_page"),
                    ArrayList<Photo>().apply {
                        optJSONArray("photos")?.let {
                            for(i in 0 until it.length()) {
                                Photo.buildFromJson(it.getJSONObject(i))?.let { it1 -> this.add(it1) } //  this.add(Photo.buildFromJson(it.getJSONObject(i)))
                            }
                        }
                    },
                    optString("prev_page"),
                    optInt("total_results")
                )
            }
            return null
        }
    }
}