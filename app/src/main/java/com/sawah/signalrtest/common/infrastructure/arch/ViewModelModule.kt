package com.sawah.signalrtest.common.infrastructure.arch


import com.sawah.signalrtest.features.students.StudentsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


var viewModelModule = module {
    viewModel { StudentsViewModel(get(), get(), androidApplication()) }
}