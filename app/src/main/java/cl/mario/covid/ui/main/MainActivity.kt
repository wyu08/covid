package cl.mario.covid.ui.main

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import cl.mario.covid.databinding.ActivityMainBinding
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

        covidViewModel.onCreate()

        covidViewModel.covidModel.observe(this, Observer {
            it
            val confirmed = it.confirmed.toString()
            val deaths = it.deaths.toString()

            binding.tvDate.text = it.date
            binding.tvConfirm.text = "Casos confirmados: $confirmed"
            binding.tvDeath.text = "Cantidad de personas fallecidas $deaths"
        })

        covidViewModel.isLoading.observe(this, Observer {
            binding.progress.isVisible = it
            binding.button.isVisible = !it
            binding.tvDate.isVisible = !it
            binding.tvConfirm.isVisible = !it
            binding.tvDeath.isVisible = !it
            binding.ivHome.isVisible = !it
        })

        binding.button.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DATE).minus(1)
            val datePickerDialog = DatePickerDialog(this, { view, year, month, day ->
                val monthformat = month.plus(1).toString().padStart(2, '0')
                val dayformat = day.toString().padStart(2, '0')
                covidViewModel.getCovidResults("$year-$monthformat-$dayformat")
            }, year, month, day)

            datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 24 * 60 * 60 * 1000 //sacandole el día vieja escuela jajaj sorry por esto
            datePickerDialog.show()
        }

    }
}