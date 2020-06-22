package com.sawah.signalrtest.common.infrastructure.arch

import androidx.lifecycle.MutableLiveData

open class BaseProgressIndicatorReasonType(var reason: String) : MutableLiveData<String>(reason) {

    companion object BaseProgressIndicatorReasonType {

        val DEFAULT =
            BaseProgressIndicatorReasonType(
                "default"
            )
        val LOADING =
            BaseProgressIndicatorReasonType(
                "loading"
            )
        val PLEASE_WAIT =
            BaseProgressIndicatorReasonType(
                "pleaseWait"
            )
        val SHOW_SWIPE_REFRESH =
            BaseProgressIndicatorReasonType(
                "showSwipeRefresh"
            )
        val HIDE_SWIPE_REFRESH =
            BaseProgressIndicatorReasonType(
                "hideSwipeRefresh"
            )
    }


}