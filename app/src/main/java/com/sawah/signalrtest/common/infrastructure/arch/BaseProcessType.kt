package com.sawah.signalrtest.common.infrastructure.arch

import androidx.lifecycle.MutableLiveData

open class BaseProcessType() : MutableLiveData<String>() {

    constructor(type: String) : this() {

        value = type
    }

    companion object BaseProcessType {

        val DEFAULT =
            BaseProcessType()

        val MOVE_TO_LOGIN =
            BaseProcessType("moveToLogin")

    }

}