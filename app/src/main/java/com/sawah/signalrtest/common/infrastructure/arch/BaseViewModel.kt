package com.sawah.signalrtest.common.infrastructure.arch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.sawah.signalrtest.common.infrastructure.SingleLiveEvent
import com.sawah.signalrtest.common.infrastructure.exception.Failure
import com.sawah.signalrtest.common.snackbar.SnackBarData


@Suppress("unused")
abstract class BaseViewModel(androidApplication: Application) :
    AndroidViewModel(androidApplication) {

    var errorIndicator: SingleLiveEvent<BaseErrorType> = SingleLiveEvent()
    var baseProcessProcessStatus: BaseProcessType = BaseProcessType()
    var progressIndicatorReason: BaseProgressIndicatorReasonType =
        BaseProgressIndicatorReasonType.DEFAULT

    var progressIndicator: SingleLiveEvent<Boolean> = SingleLiveEvent()
    var snackBackDataObservable: SingleLiveEvent<SnackBarData> = SingleLiveEvent()

    private var isDataAvailable = true
    private var initialised = false
    private var isRtl = true


    var failure: SingleLiveEvent<Failure> =
        SingleLiveEvent() // the failure should just emit once. We don't want failures to emit when an observer resubscribes to this property.

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
        hideProgressDialog()
        when (failure) {
            is Failure.AuthError -> setProcessStatus(BaseProcessType.MOVE_TO_LOGIN.value!!)
            is Failure.NetworkConnection -> errorIndicator.value =
                BaseErrorType.CHECK_INTERNET_ERROR
            else -> errorIndicator.value = BaseErrorType.UNKNOWN_ERROR
        }
    }

    open fun init() {
        //baseProcessProcessStatus=BaseProcessType.DEFAULT
        isDataAvailable = true
        initialised = false
        isRtl = true
    }

    fun isRtl(): Boolean {

        return isRtl
    }

    fun setRtl(rtl: Boolean) {

        this.isRtl = rtl
    }


    fun resetErrorIndicator() {

        errorIndicator.value = BaseErrorType.NO_ERROR
    }

    fun setError(error: BaseErrorType) {

        errorIndicator.value = error
        setError(BaseErrorType.NO_ERROR)
    }

    fun setProcessStatus(processStatus: String?) {

        baseProcessProcessStatus.value = processStatus

    }

    fun resetProcessStatus() {
        setProcessStatus(BaseProcessType.DEFAULT.value)
    }

    fun setProgressIndicatorReason(reason: String) {

        progressIndicatorReason.value = reason
    }


    fun showProgressDialog() {

        progressIndicator.value = true
    }

    fun hideProgressDialog() {
        progressIndicator.value = false
    }

    fun setSnackbarData(snackBarData: SnackBarData) {

        snackBackDataObservable.value = snackBarData

    }

    fun getIsDataAvailable(): Boolean {

        return isDataAvailable
    }

    @Suppress("UNUSED_PARAMETER")
    fun setIsDataAvailable(flag: Boolean) {

        isDataAvailable
    }


    private var failureObserver: SingleLiveEvent<Failure> =
        SingleLiveEvent() // the failure should just emit once. We don't want failures to emit when an observer resubscribes to this property.

    fun failure(failureError: Failure) {

        failureObserver.value = failureError

    }


    open fun getMyTag(): String? {

        return this.javaClass.canonicalName
    }


}