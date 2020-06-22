package com.sawah.signalrtest.common.infrastructure.arch

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.sawah.signalrtest.common.customView.dialog.MyProgressDialog
import com.sawah.signalrtest.common.infrastructure.extensions.observe
import com.sawah.signalrtest.common.snackbar.SnackBarData
import com.sawah.signalrtest.common.snackbar.SnackbarUtils.showSnackbar
import com.sawah.vehicledashboardapp.R
import org.koin.android.ext.android.inject


@Suppress("unused")
abstract class BaseFragment : Fragment() {

    private var isInProgress = true
    private val isProgressVisible = "isInProgress"
    private val progressDialog: MyProgressDialog by inject()
    private lateinit var progressDialogMessage: String
    protected lateinit var binding: ViewDataBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, layoutResource(), container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        snackBarView = binding.root
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel().init()
        progressDialogMessage = getString(R.string.please_wait)
        setProcessStatusCall()
        setProgressIndicatorCallback()
        progressIndicatorObserver()
        setErrorIndicatorCallback()
        setSnackBarCallBack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initialise()

    }


    private fun setProcessStatusCall() {

        observe(getViewModel().baseProcessProcessStatus) {


            when (it) {
                BaseProcessType.DEFAULT.value -> {
                    //Do Nothing
                }
                else -> {

                    onViewModelProcessStatus(it)

                }


            }


        }
    }

    private fun setProgressIndicatorCallback() {

        observe(getViewModel().progressIndicatorReason) {

            when (it) {

                BaseProgressIndicatorReasonType.DEFAULT.reason -> {
                    //Do Nothing
                }
                BaseProgressIndicatorReasonType.LOADING.reason -> {
                    progressDialogMessage = getString(R.string.loading)
                }
                BaseProgressIndicatorReasonType.PLEASE_WAIT.reason -> progressDialogMessage =
                    getString(R.string.please_wait)
                else -> {
                    onProgressIndicatorReason(it)
                }
            }

        }
    }

    private fun progressIndicatorObserver() {

        observe(getViewModel().progressIndicator) { showProgress ->

            progressDialog.run {
                if (showProgress) {

                    getFragment().childFragmentManager.run {
                        progressDialog.setMessage(progressDialogMessage)
                        if (!progressDialog.isVisible && !progressDialog.isAdded) {
                            progressDialog.show(this, "progressDialog")
                        }

                    }


                } else {

                    if (progressDialog.isAdded) {
                        progressDialog.dismiss()
                    }

                }


            }


        }
    }


    @Suppress("unused")
    fun hideKeyboard() {
        activity?.apply {

            val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            //Find the currently focused view, so we can grab the correct window token from it.
            var view = currentFocus
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = View(this)
            }
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

    }


    fun hideKeyboard(viewPassed: View?) {
        var view = viewPassed

        activity?.apply {

            val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            //Find the currently focused view, so we can grab the correct window token from it.
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = View(this)
            }
            imm.hideSoftInputFromWindow(view!!.windowToken, 0)
        }


    }

    private fun setErrorIndicatorCallback() {

        observe(getViewModel().errorIndicator) { error ->

            when (error) {
                BaseErrorType.NO_ERROR -> {
                }
                BaseErrorType.CHECK_INTERNET_ERROR -> {
                    getViewModel().setSnackbarData(
                        SnackBarData(
                            getString(R.string.check_internet),
                            SnackBarData.ERROR
                        )
                    )
                }

                BaseErrorType.UNKNOWN_ERROR -> getViewModel().setSnackbarData(
                    SnackBarData(
                        getString(R.string.something_went_wrong),
                        SnackBarData.ERROR
                    )
                )
                else -> {
                    onViewModelError(error.type)

                }

            }


        }

        // LoginErrorType.EMPTY_USERNAME.type
    }

    private fun setSnackBarCallBack() {

        observe(getViewModel().snackBackDataObservable) { snackBarData ->

            when (snackBarData.messageType) {
                SnackBarData.DEFAULT_TYPE -> {
                }
                else -> {

                    showSnackbar(snackBarView, snackBarData, activity)
                    getViewModel().setSnackbarData(
                        SnackBarData(
                            "",
                            SnackBarData.DEFAULT_TYPE
                        )
                    )
                }

            }

        }

    }

    protected var snackBarView: View? = null


    protected abstract fun layoutResource(): Int
    protected abstract fun getFragment(): BaseFragment
    protected abstract fun getMyTag(): String
    protected abstract fun getViewModel(): BaseViewModel
    protected abstract fun initialise()
    protected abstract fun onViewModelError(error: String)
    protected abstract fun onViewModelProcessStatus(processStatus: String)
    protected abstract fun onProgressIndicatorReason(indicatorReason: String)


}