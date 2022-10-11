package cl.mario.covid.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import cl.mario.covid.databinding.ActivityMainBinding
import cl.mario.covid.util.CalendarManager
import cl.mario.covid.util.State
import cl.mario.covid.util.dateToStringApi
import dagger.hilt.android.AndroidEntryPoint

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

    private fun initListener() {
        binding.chooseDateDialogBtn.setOnClickListener {
            CalendarManager(binding.root.context).apply {
                setOnSelectedListener { date ->
                    covidViewModel.getCovidResults(dateToStringApi(date))
                }
                show()
            }
        }
    }
}
