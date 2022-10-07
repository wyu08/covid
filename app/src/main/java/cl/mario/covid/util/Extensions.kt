package cl.mario.covid.util

fun <T> T?.orElse(value: T) = this ?: value