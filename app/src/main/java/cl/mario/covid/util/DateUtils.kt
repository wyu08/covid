package cl.mario.covid.util

import java.util.*

fun dateToStringApi(date: Date): String {
    val calendar = Calendar.getInstance()
    calendar.time = date
    val monthformat = calendar.get(Calendar.MONTH).plus(1).toString().padStart(2,'0')
    val dayformat = calendar.get(Calendar.DATE).toString().padStart(2,'0')
    return "${calendar.get(Calendar.YEAR)}-$monthformat-$dayformat"
}