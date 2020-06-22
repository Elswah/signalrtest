package com.sawah.signalrtest.data.source.remote

import okhttp3.Request

@Suppress("unused")
class IpvRequestHeaders(
    private var xGateWayAPiKey: String,
    var language: String = "en",
    var accessToken: String?
) {

    fun map(builder: Request.Builder) {

        //builder.addHeader("x-Gateway-APIkey",xGateWayAPiKey)
        //builder.addHeader("Accept-Language", language)
        builder.addHeader("Accept", "application/json")
        builder.addHeader("Content-Type", "application/json")

        /*  accessToken?.let {
              builder.addHeader()

          }*/
    }
}