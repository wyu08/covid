package cl.mario.covid.util

import android.app.DatePickerDialog
import cl.mario.covid.ui.main.MainActivity
import java.util.*

class CalendarManager(private val activity: MainActivity) {

    private var dateSelectedListener: ((date: Date) -> Unit)? = null

    fun setOnSelectedListener(listener: (date: Date) -> Unit) {
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
                    val calendar = Calendar.getInstance()
                    calendar.set(year,month,day)
                    dateSelectedListener?.invoke(calendar.time)
                }, get(Calendar.YEAR), get(Calendar.MONTH), get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.apply {
                datePicker.maxDate = System.currentTimeMillis() - 24 * 60 * 60 * 1000L
                show()
            }
        }
    }
}