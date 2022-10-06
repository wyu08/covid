package cl.mario.covid.util

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

open class DefaultViewModel() : ViewModel() {
    val isError = ObservableBoolean(false)
    val hasContent = ObservableBoolean(false)
    val isLoading = ObservableBoolean(false)

    fun <T> handleView(
        flow: Flow<State<T>>,
        liveData: MutableLiveData<State<T>>,
        onSuccess: (T) -> Unit = {}
    ) {
        viewModelScope.launch {
            flow.collect {
                if(it is State.Success){
                    onSuccess(it.data)
                }
                liveData.postValue(it)
                setStateView(it)
            }
        }
    }

    private fun <T> setStateView(state: State<T>) {
        when (state) {
            is State.Error -> setErrorView()
            is State.Loading -> setLoading()
            is State.Success -> setHasContent()
        }
    }

    private fun setErrorView() {
        isError.set(true)
        isLoading.set(false)
        hasContent.set(false)
    }

    private fun setLoading() {
        isError.set(false)
        isLoading.set(true)
        hasContent.set(false)
    }

    private fun setHasContent() {
        isError.set(false)
        isLoading.set(false)
        hasContent.set(true)
    }
}