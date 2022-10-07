package cl.mario.covid.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.mario.covid.data.useCases.GetDataCovidUseCase
import cl.mario.covid.ui.viewData.CovidResultViewData
import cl.mario.covid.util.DefaultViewModel
import cl.mario.covid.util.State
import cl.mario.covid.util.getCurrentDateFormatApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CovidViewModel @Inject constructor(private val getDataCovidUseCase: GetDataCovidUseCase) :
    DefaultViewModel() {

    var lastDateRequest: String = ""
        private set

    private val _covidInfoStateLiveData = MutableLiveData<State<CovidResultViewData>>()
    val covidInfoStateLiveData: LiveData<State<CovidResultViewData>> = _covidInfoStateLiveData

    private val _covidDataResultLiveData = MutableLiveData<CovidResultViewData>()
    val covidDataResultLiveData: LiveData<CovidResultViewData> = _covidDataResultLiveData

    fun getCovidResults(date: String = getCurrentDateFormatApi()) =
        handleView(getDataCovidUseCase.execute(date), _covidInfoStateLiveData,
            onLoading = {
                        lastDateRequest = date
            },
            onSuccess = {
                _covidDataResultLiveData.postValue(it)
            })

}

