package com.livingtechusa.gotjokes.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.json.JSONObject



@JsonClass(generateAdapter = true)
data class DogFact(
    @Json(name = "facts")
    val facts: List<String> = listOf(),
    @Json(name = "success")
    val success: Boolean = false // true
) {
    companion object {
        @JvmStatic
        fun buildFromJson(jsonObject: JSONObject?): DogFact? {

            jsonObject?.run {
                return DogFact(
                    ArrayList<String>().apply {
                        optJSONArray("facts")?.let {
                            for(i in 0 until it.length()) {
                                this.add((it.getJSONObject(i).toString()))
                            }
                        }
                    },
                    optBoolean("success")
                )
            }
            return null
        }
    }
}