//package com.livingtechusa.gotjokes.data.api.model
//
//
//import com.squareup.moshi.Json
//import com.squareup.moshi.JsonClass
//import org.json.JSONObject
//
//
//
//@JsonClass(generateAdapter = true)
//data class MemeMakerImage(
//    @Json(name = "code")
//    val code: Int? = 0, // 200
//    @Json(name = "data")
//    val `data`: List<Data>,
//    @Json(name = "message")
//    val message: String? = "" // GET successful
//) {
//    @JsonClass(generateAdapter = true)
//    data class Data(
//        @Json(name = "bottomText")
//        val bottomText: String? = "", // Good!
//        @Json(name = "ID")
//        val iD: Int? = 0, // 1
//        @Json(name = "image")
//        val image: String? = "", // http://imgflip.com/s/meme/Grumpy-Cat.jpg
//        @Json(name = "name")
//        val name: String? = "", // Grumpy Cat
//        @Json(name = "rank")
//        val rank: Int? = 0, // 10
//        @Json(name = "tags")
//        val tags: String? = "", // Tardar Sauce, Tabatha Bundesen, Felis domesticus
//        @Json(name = "topText")
//        val topText: String? = ""
//    ) {
//        companion object {
//            @JvmStatic
//            fun buildFromJson(jsonObject: JSONObject?): Data? {
//
//                jsonObject?.run {
//                    return Data(
//                        optString("bottomText"),
//                        optInt("ID"),
//                        optString("image"),
//                        optString("name"),
//                        optInt("rank"),
//                        optString("tags"),
//                        optString("topText")
//                    )
//                }
//                return null
//            }
//        }
//    }
//    companion object {
//        @JvmStatic
//        fun buildFromJson(jsonObject: JSONObject?): MemeMakerImage? {
//
//            jsonObject?.run {
//                return MemeMakerImage(
//                    optInt("code"),
//                    ArrayList<Data>().apply {
//                        optJSONArray("data")?.let {
//                            for(i in 0 until it.length()) {
//                                Data.buildFromJson(it.getJSONObject(i))?.let { it1 -> this.add(it1) }
//                            }
//                        }
//                    },
//                    optString("message")
//                )
//            }
//            return null
//        }
//    }
//}