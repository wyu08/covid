package cl.mario.covid.ui.main

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import cl.mario.covid.data.models.CovidResultsData
import cl.mario.covid.databinding.ActivityMainBinding
import cl.mario.covid.ui.viewData.CovidResultViewData
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

            datePickerDialog.datePicker.maxDate =
                System.currentTimeMillis() - 24 * 60 * 60 * 1000 //sacandole el día vieja escuela jajaj sorry por esto
            datePickerDialog.show()
        }

    }

    override fun onStart() {
        super.onStart()
        binding.viewModel = covidViewModel
        binding.lifecycleOwner = this
        covidViewModel.getCovidResults()
    }
}
