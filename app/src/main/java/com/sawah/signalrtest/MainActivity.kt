package com.sawah.signalrtest

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.sawah.signalrtest.features.healthcheck.ServerHealthCheck
import com.sawah.vehicledashboardapp.R


class MainActivity : AppCompatActivity() {
    var handler: Handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        runServerHealthService()

    }


    private val periodicUpdate: Runnable = object : Runnable {
        override fun run() {
            try {
                handler.postDelayed(this, 3000)
                val intent = Intent(applicationContext, ServerHealthCheck::class.java)
                ServerHealthCheck.enqueueWork(this@MainActivity, intent)
            } catch (e: Exception) {

            }
        }
    }

    private fun runServerHealthService() {
        try {
            handler.post(periodicUpdate)
        } catch (e: Exception) {

        }
    }

    override fun onDestroy() {
        try {
            handler.removeCallbacks(periodicUpdate)
        } catch (e: Exception) {

        }
        super.onDestroy()
    }


}
