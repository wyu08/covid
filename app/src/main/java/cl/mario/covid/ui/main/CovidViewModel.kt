package cl.mario.covid.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.mario.covid.data.models.Data
import cl.mario.covid.data.remote.CovidApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CovidViewModel @Inject constructor(private val getCovidApi: CovidApi) : ViewModel() {

    val covidModel = MutableLiveData<Data>()
    val isLoading = MutableLiveData<Boolean>()
    var date = "2022-09-13"
    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getCovidApi.getData(date)
            if (result.isSuccessful)
                covidModel.postValue(result.body()!!.data)
            isLoading.postValue(false)
        }
    }

    fun getCovidResults(date: String){
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getCovidApi.getData(date)
            System.out.println(result)
            if (result.isSuccessful)
                covidModel.postValue(result.body()!!.data)
            isLoading.postValue(false)
        }
    }
}

