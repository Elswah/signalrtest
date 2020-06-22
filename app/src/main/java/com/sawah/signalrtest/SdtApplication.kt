package com.sawah.signalrtest

import android.app.Application
import android.content.Context
import com.sawah.signalrtest.common.infrastructure.arch.sdtModule
import com.sawah.signalrtest.common.infrastructure.arch.viewModelModule
import com.sawah.signalrtest.common.infrastructure.signalr.signalRModule
import com.sawah.signalrtest.data.source.remote.healthNetwork
import com.sawah.signalrtest.features.logs.Log
import com.sawah.signalrtest.features.students.routeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class SdtApplication : Application() {
    companion object {
        private var context: Context? = null
        fun getContext(): Context {
            return context!!.applicationContext
        }

    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@SdtApplication)

            modules(
                listOf(
                    sdtModule,
                    healthNetwork,
                    signalRModule,
                    routeModule,
                    viewModelModule
                )
            )
        }
        context = this
        debugMode()
    }

    private fun debugMode() {
        Log.setDebug(true)
        Log.setPath("dashboardLogger/")
    }


}