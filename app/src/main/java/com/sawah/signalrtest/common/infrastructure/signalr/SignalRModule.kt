package com.sawah.signalrtest.common.infrastructure.signalr

import com.sawah.vehicledashboardapp.BuildConfig
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

var signalRModule = module {
    single { SdtSignalR(get(), BuildConfig.SIGNALR_ENDPOINT + "/Events", androidApplication()) }
}