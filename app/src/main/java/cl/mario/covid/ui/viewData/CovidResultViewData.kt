package cl.mario.covid.ui.viewData

import cl.mario.covid.util.getCurrentDateWithSpanishFormat

data class CovidResultViewData(
    val confirmed: Int = 0,
    val death: Int = 0,
    val date: String = getCurrentDateWithSpanishFormat()
)