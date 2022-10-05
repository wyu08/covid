package cl.mario.covid.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun getCurrentDateWithSpanishFormat(date: String = getCurrentDateFormatApi()): String {
    val textDate = LocalDate.parse(date)
    return "${textDate.dayOfMonth} de ${textDate.month} del ${textDate.year}"
}

fun getCurrentDateFormatApi(): String {
    val today = LocalDate.now().minusDays(1)
    return today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}