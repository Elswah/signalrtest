package com.sawah.signalrtest.features.healthcheck

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService
import com.sawah.signalrtest.common.infrastructure.signalr.SdtSignalR
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServerHealthCheck : JobIntentService() {
    private val service: HealthService by inject()
    private val signalRInstance: SdtSignalR by inject()

    companion object {
        lateinit var ctx: Context
        private const val JOB_ID = 102
        fun enqueueWork(context: Context, intent: Intent) {
            this.ctx = context
            enqueueWork(context, ServerHealthCheck::class.java, JOB_ID, intent)
        }
    }

    override fun onHandleWork(intent: Intent) {
        Log.d("sawahsignalr", "healthCheck working " + signalRInstance.getConnectionId())
        checkSystemHealth()
    }

    private fun checkSystemHealth() {
        val healthCall: Call<String> =
            service.getSystemHealthStatus(signalRInstance.getConnectionId())
        healthCall.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                if (response.isSuccessful) {

                } else if (signalRInstance.isConnected()) {
                    // Log.d("sawahsignalr", "Reconnecting")
                    signalRInstance.closeConnection()
                    signalRInstance.tryConnectEventHub {
                        //  Log.d("sawahsignalr", "Connected")
                        //  Log.d("sawahsignalr", signalRInstance.getConnectionId())
                    }
                }
            }
        })
    }
}