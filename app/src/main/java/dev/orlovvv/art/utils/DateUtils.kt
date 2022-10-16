package dev.orlovvv.art.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private const val INPUT_FORMAT_DATE = "yyyy-MM-dd'T'HH:mm:ss"
    private const val OUTPUT_FORMAT_DATE = "HH:mm dd MMM"

    fun formatDate(date: String): String {
        val outputFormat: DateFormat = SimpleDateFormat(OUTPUT_FORMAT_DATE, Locale.ENGLISH)
        val inputFormat: DateFormat = SimpleDateFormat(INPUT_FORMAT_DATE, Locale.ENGLISH)
        return outputFormat.format(inputFormat.parse(date))
    }
}