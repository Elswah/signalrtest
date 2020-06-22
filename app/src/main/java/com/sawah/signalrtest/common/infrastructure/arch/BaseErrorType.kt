package com.sawah.signalrtest.common.infrastructure.arch


open class BaseErrorType(var type: String) {


    companion object BaseErrorType {

        val NO_ERROR =
            BaseErrorType("default")
        val CHECK_INTERNET_ERROR =
            BaseErrorType("noInternet")
        val UNKNOWN_ERROR =
            BaseErrorType("unknown")
    }


}