package com.sawah.signalrtest.data.constants

import com.google.gson.annotations.SerializedName

@Suppress("unused")
enum class MessageType {

    @SerializedName("2090")
    ERROR,

    @SerializedName("1000")
    SUCCESS,
    INFORMATION
}