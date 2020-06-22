package com.sawah.signalrtest.features.students

import retrofit2.Call
import retrofit2.http.GET

interface RouteApiService {
    @GET("api/Committee/GetRouteData")
    fun getAllRoutesData(): Call<String>
}