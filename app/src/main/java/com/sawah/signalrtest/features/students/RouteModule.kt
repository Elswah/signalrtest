package com.sawah.signalrtest.features.students

import com.sawah.signalrtest.features.healthcheck.HealthService
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

var routeModule = module {
    single { get<Retrofit>(named("health")).create(RouteApiService::class.java) }
    single { get<Retrofit>(named("health")).create(HealthService::class.java) }
    single { StudentRepository(get()) }
    single { StudentUseCase(get(), get()) }

}