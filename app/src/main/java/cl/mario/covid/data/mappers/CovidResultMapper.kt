package cl.mario.covid.data.mappers

import cl.mario.covid.data.models.CovidResultsData
import cl.mario.covid.ui.viewData.CovidResultViewData
import cl.mario.covid.util.orElse
import javax.inject.Inject

class CovidResultMapper @Inject constructor() {
    fun executeMapping(data: CovidResultsData?) =
        if (data == null) {
            CovidResultViewData()
        } else {
            CovidResultViewData(data.info?.confirmed.orElse(0), data.info?.deaths.orElse(0))
        }
}
