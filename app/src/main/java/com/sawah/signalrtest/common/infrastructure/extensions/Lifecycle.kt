package com.sawah.signalrtest.common.infrastructure.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.sawah.signalrtest.common.infrastructure.exception.Failure

fun <T : Any, L : LiveData<T>> AppCompatActivity.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <L : LiveData<Failure>> AppCompatActivity.failure(liveData: L, body: (Failure?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <T : Any, L : LiveData<T>> Fragment.observe(liveData: L, body: (T) -> Unit) =
    liveData.observe(this, Observer(body))

@Suppress("unused")
fun <L : LiveData<Failure>> Fragment.fault(liveData: L, body: (Failure?) -> Unit) =
    liveData.observe(viewLifecycleOwner, Observer(body))