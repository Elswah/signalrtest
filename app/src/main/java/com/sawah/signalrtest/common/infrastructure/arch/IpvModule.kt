package com.sawah.signalrtest.common.infrastructure.arch

import android.content.Context
import com.sawah.signalrtest.common.customView.dialog.MyProgressDialog
import com.sawah.signalrtest.data.source.local.SharedPrefsUtil
import com.sawah.signalrtest.utils.AppUtils
import com.sawah.vehicledashboardapp.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

var sdtModule = module {

    single {
        SharedPrefsUtil(
            androidApplication().getSharedPreferences(
                "mySharedD_" + BuildConfig.APPLICATION_ID,
                Context.MODE_PRIVATE
            )
        )
    }
    single { MyProgressDialog() }
    single { AppUtils() }
    single { CoroutineScope(Dispatchers.IO + Job()) }

}