package cl.mario.covid.util

import android.app.DatePickerDialog
import cl.mario.covid.ui.main.MainActivity
import java.util.*

class CalendarManager(private val activity: MainActivity) {

    private var dateSelectedListener: ((date: String) -> Unit)? = null

    fun setOnSelectedListener(listener: (date: String) -> Unit) {
        dateSelectedListener = listener
    }

    fun show(dateSelected: Date = Calendar.getInstance().time) {
        initConfig(dateSelected)
    }
    private fun initConfig(dateSelected: Date){

        val calendarSelected = Calendar.getInstance()
        calendarSelected.time = dateSelected

        calendarSelected.apply {
            val datePickerDialog = DatePickerDialog(
                activity, { _, year, month, day ->
                    dateSelectedListener?.invoke(formatDateToApi(year,month,day))
                }, get(Calendar.YEAR), get(Calendar.MONTH), get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.apply {
                datePicker.maxDate = System.currentTimeMillis() - 24 * 60 * 60 * 1000L
                show()
            }
        }
    }

    private fun formatDateToApi(year:Int,month:Int,day:Int) : String{
        val monthformat = month.plus(1).toString().padStart(2,'0')
        val dayformat = day.toString().padStart(2,'0')
        return "$year-$monthformat-$dayformat"
    }
}