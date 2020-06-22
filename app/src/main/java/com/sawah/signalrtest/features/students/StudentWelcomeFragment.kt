package com.sawah.signalrtest.features.students

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.sawah.signalrtest.common.infrastructure.arch.BaseFragment
import com.sawah.signalrtest.common.infrastructure.extensions.observe
import com.sawah.vehicledashboardapp.R
import kotlinx.android.synthetic.main.map_layout.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class StudentWelcomeFragment : BaseFragment() {
    private val studentsViewModel: StudentsViewModel by sharedViewModel()

    override fun layoutResource() = R.layout.exam_layout

    override fun getFragment() = this

    override fun getMyTag() = javaClass.name

    override fun getViewModel() = studentsViewModel

    override fun initialise() {


        // need to enhance speech recorded engine not support arabic
        observe(studentsViewModel.studentObject) {

        }

        observe(studentsViewModel.roadDataWithStops) {

        }
        observe(studentsViewModel.selectedRoute) {
        }
        // need to enhance speech recorded engine not support arabic
        observe(studentsViewModel.examEnded) {

        }
        observe(studentsViewModel.languageSelectedBasedNationality) {


        }

    }


    private fun initSignalRConnection() {
        try {
            studentsViewModel.initialiseSignalR {
                try {
                    Handler(Looper.getMainLooper()).post {
                        try {
                            Toast.makeText(
                                requireContext(),
                                "signalR Connected fine",
                                Toast.LENGTH_SHORT
                            ).show()
                        } catch (e: Exception) {
                            Log.d("error", "error in toast")
                        }
                    }
                } catch (e: Exception) {

                }
            }
        } catch (e: Exception) {
            Log.d("error", "error in signalR")
        }
    }


    override fun onViewModelError(error: String) {
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onViewModelProcessStatus(processStatus: String) {
    }


    override fun onProgressIndicatorReason(indicatorReason: String) {
    }


    override fun onStart() {
        super.onStart()
        Log.d("cycle", "onstart")
    }


    override fun onStop() {
        super.onStop()
        Log.d("cycle", "onstop")
    }

    override fun onPause() {
        super.onPause()
        studentsViewModel.removeSignalRCallBack()
        studentsViewModel.closeConnectionSignalR()
        Log.d("cycle", "onpause")

    }

    override fun onResume() {
        super.onResume()
        // i call connection in resume because in tablet user click home
        Log.d("cycle", "onResume")
        initSignalRConnection()
        try {
            mMapView?.resume()
        } catch (e: Exception) {

        }
    }


}