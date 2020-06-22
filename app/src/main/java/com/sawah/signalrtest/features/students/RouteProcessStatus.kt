package com.sawah.signalrtest.features.students

import com.sawah.signalrtest.common.infrastructure.arch.BaseProcessType


class RouteProcessStatus(type: String) : BaseProcessType(type) {

    companion object RouteProcessStatus {

        var SUCCESSFUL_DATA_COME = RouteProcessStatus("DataComeSuccess")
    }
}