package com.sawah.signalrtest.features.healthcheck

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HealthService {
    @GET("api/System/SignalRConnected")
    fun getSystemHealthStatus(@Query("connectionId") connectionId: String): Call<String>
}