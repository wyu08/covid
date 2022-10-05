package cl.mario.covid.data.useCases

import cl.mario.covid.data.mappers.CovidResultMapper
import cl.mario.covid.data.remote.CovidApi
import cl.mario.covid.ui.viewData.CovidResultViewData
import cl.mario.covid.util.State
import cl.mario.covid.util.getDataSafe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetDataCovidUseCase @Inject constructor(
    private val covidApi: CovidApi,
    private val covidResultMapper: CovidResultMapper
) {
    fun execute(date: String): Flow<State<CovidResultViewData>> {
        return flow {
            emit(State.loading())
            val result = covidApi.getDataSafe(date)
            if (result != null && result.isSuccessful) {
                emit(State.success(covidResultMapper.executeMapping(result.body())))
            } else {
                if(result == null) emit(State.error("Error")) else emit(State.error(result.message()))
            }
        }
    }
}