package com.hitech.pickit.media.presentation.utili.db_converters


import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

object Converters {
    @TypeConverter
    fun fromStringList(value: List<String>?): String? {
        return value?.let { Json.encodeToString(it) }
    }

    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        return value?.let { Json.decodeFromString(it) }
    }


    @TypeConverter
    fun fromIntList(value: List<Int>?): String? {
        return value?.let { Json.encodeToString(it) }
    }

    @TypeConverter
    fun toIntList(value: String?): List<Int>? {
        return value?.let { Json.decodeFromString(it) }
    }
}