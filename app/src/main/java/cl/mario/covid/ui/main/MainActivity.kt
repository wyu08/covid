package cl.mario.covid.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cl.mario.covid.R
import cl.mario.covid.routermodule.service.AppReceivedServiceImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var appReceivedServiceImpl: AppReceivedServiceImpl = AppReceivedServiceImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
    }

    override fun onStart() {
        super.onStart()
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                appReceivedServiceImpl.getCovidResultFragment()
            )
            .commitNow()
    }
}
