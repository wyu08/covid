package cl.mario.covid.data.useCases

import cl.mario.covid.data.mappers.CovidResultMapper
import cl.mario.covid.data.remote.CovidApi
import cl.mario.covid.ui.viewData.CovidResultViewData
import cl.mario.covid.util.Result
import cl.mario.covid.util.State
import cl.mario.covid.util.safeRequest
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
            val result = safeRequest { covidApi.getData(date) }

            when(result){
                is Result.Error -> emit(State.error(result.message))
                is Result.Success -> emit(State.success(covidResultMapper.executeMapping(result.data)))
            }
        }
    }
}