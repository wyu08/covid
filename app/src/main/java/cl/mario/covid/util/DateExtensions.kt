package cl.mario.covid.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun getCurrentDateWithSpanishFormat(): String {
    val textDate = LocalDate.parse(getCurrentDateFormatApi())
    return "${textDate.dayOfMonth} de ${textDate.month} del ${textDate.year}"
}

fun getCurrentDateFormatApi(): String {
    val today = LocalDate.now()
    return today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}