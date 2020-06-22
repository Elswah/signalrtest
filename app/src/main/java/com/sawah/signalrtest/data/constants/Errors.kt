package com.sawah.signalrtest.data.constants

import com.google.gson.annotations.SerializedName

@Suppress("unused")
enum class Errors {
    @SerializedName("1")
    USER_ADD_FAILURE,

    EMPTY_ERROR
}