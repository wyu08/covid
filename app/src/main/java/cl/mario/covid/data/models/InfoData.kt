package cl.mario.covid.data.models

import com.google.gson.annotations.SerializedName

data class InfoData(
    @SerializedName("active")
    val active: Int? = null,
    @SerializedName("active_diff")
    val activeDiff: Int? = null,
    @SerializedName("confirmed")
    val confirmed: Int? = null,
    @SerializedName("confirmed_diff")
    val confirmedDiff: Int? = null,
    @SerializedName("date")
    val date: String? = null,
    @SerializedName("deaths")
    val deaths: Int? = null,
    @SerializedName("deaths_diff")
    val deathsDiff: Int? = null,
    @SerializedName("fatality_rate")
    val fatalityRate: Double? = null,
    @SerializedName("last_update")
    val lastUpdate: String? = null,
    @SerializedName("recovered")
    val recovered: Int? = null,
    @SerializedName("recovered_diff")
    val recoveredDiff: Int? = null
)