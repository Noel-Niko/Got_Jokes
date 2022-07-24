package com.livingtechusa.gotjokes.data.database

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
}

fun convertLocalDateTimeToDate(dateToConvert: LocalDateTime): Date? {
    return Date
        .from(
            dateToConvert.atZone(ZoneId.systemDefault())
                .toInstant()
        )
}