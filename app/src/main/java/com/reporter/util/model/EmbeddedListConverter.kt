package com.reporter.util.model

import androidx.room.TypeConverter
import com.reporter.common.Texts
import com.reporter.common.splitIntoList

class EmbeddedListConverter {

    @TypeConverter
    fun splitStringList(value: String): List<String> =
        value.splitIntoList(Texts.DATA_SEPARATOR_CHAR)

    @TypeConverter
    fun joinStringList(list: List<String>): String = list.joinToString(Texts.DATA_SEPARATOR)
}