package com.livingtechusa.gotjokes.data.database

import android.net.Uri
import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun convertStringToUri(string: String): Uri? {
        return Uri.parse(string)
    }

    @TypeConverter
    fun convertUriToString(uri: Uri): String? {
        return uri.toString()
    }
}

fun convertLocalDateTimeToDate(dateToConvert: LocalDateTime): Date? {
    return Date
        .from(
            dateToConvert.atZone(ZoneId.systemDefault())
                .toInstant()
        )
}
