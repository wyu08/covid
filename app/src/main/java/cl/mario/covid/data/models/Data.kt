package cl.mario.covid.data.models

data class Data(
    val active: Int,
    val active_diff: Int,
    val confirmed: Int,
    val confirmed_diff: Int,
    val date: String,
    val deaths: Int,
    val deaths_diff: Int,
    val fatality_rate: Double,
    val last_update: String,
    val recovered: Int,
    val recovered_diff: Int
)