package com.sawah.signalrtest.features.students

import com.sawah.signalrtest.common.infrastructure.exception.Failure
import com.sawah.signalrtest.common.infrastructure.extensions.requestBlocking
import com.sawah.signalrtest.common.infrastructure.functional.Either


class StudentRepository constructor(private val routeApiService: RouteApiService) {
    fun getRouteData(): Either<Failure, String> =
        routeApiService.getAllRoutesData().requestBlocking()
}