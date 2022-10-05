package cl.mario.covid.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.mario.covid.data.useCases.GetDataCovidUseCase
import cl.mario.covid.ui.viewData.CovidResultViewData
import cl.mario.covid.util.State
import cl.mario.covid.util.getCurrentDateFormatApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CovidViewModel @Inject constructor(private val getDataCovidUseCase: GetDataCovidUseCase) :
    ViewModel() {

    val covidInfoStateMutable = MutableLiveData<State<CovidResultViewData>>()

    fun getCovidResults(date: String = getCurrentDateFormatApi()) {
        viewModelScope.launch {
            getDataCovidUseCase.execute(date).collect {
                covidInfoStateMutable.postValue(it)
            }
        }
    }
}

