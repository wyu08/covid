package cl.mario.covid.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import cl.mario.covid.databinding.ActivityMainBinding
import cl.mario.covid.util.CalendarManager
import cl.mario.covid.util.State
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val covidViewModel: CovidViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.viewModel = covidViewModel
        binding.lifecycleOwner = this
        initObserver()
        initListener()
        covidViewModel.getCovidResults()
    }

    private fun initObserver() {
        covidViewModel.covidInfoStateLiveData.observe(this) {
            if(it is State.Error ){
                binding.errorView.loadError(it.message){
                    covidViewModel.getCovidResults(covidViewModel.lastDateRequest)
                }
            }
        }
    }

    private fun initListener(){
        binding.apply {
            button.setOnClickListener {
                openCalendar()
            }
        }
    }

    private fun openCalendar(){
        val calendar = CalendarManager(this)
        calendar.show()
        calendar.setOnSelectedListener {
            covidViewModel.getCovidResults(it)
        }
    }
}
