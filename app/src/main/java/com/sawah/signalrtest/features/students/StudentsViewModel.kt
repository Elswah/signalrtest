package com.sawah.signalrtest.features.students

import android.app.Application
import android.util.Log
import com.esri.arcgisruntime.tasks.networkanalysis.Stop
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sawah.signalrtest.common.infrastructure.SingleLiveEvent
import com.sawah.signalrtest.common.infrastructure.arch.BaseViewModel
import com.sawah.signalrtest.common.infrastructure.signalr.SdtSignalR
import com.sawah.signalrtest.common.infrastructure.signalr.SignalRCallback
import com.sawah.signalrtest.features.students.model.RoutesData
import com.sawah.signalrtest.features.students.model.Student

import org.json.JSONArray

class StudentsViewModel(
    private val studentUseCase: StudentUseCase,
    private val sdtSignalR: SdtSignalR,
    application: Application
) : BaseViewModel(application) {

    var RouteData: SingleLiveEvent<String> = SingleLiveEvent()
    var startNavigation: SingleLiveEvent<Boolean> = SingleLiveEvent()
    var studentObject: SingleLiveEvent<Student> = SingleLiveEvent()
    var roadDataWithStops: SingleLiveEvent<ArrayList<RoutesData>> = SingleLiveEvent()
    var selectedRoute: SingleLiveEvent<Int> = SingleLiveEvent()
    var languageSelectedBasedNationality: SingleLiveEvent<String> = SingleLiveEvent()
    var examEnded: SingleLiveEvent<String> = SingleLiveEvent()
    var stopList: ArrayList<Stop> = ArrayList()

    fun initialiseSignalR(callBack: () -> Unit) {
        sdtSignalR.tryConnectEventHub(callBack)
        registerCallBack()
    }

    fun listenNextStudent() {
        sdtSignalR.nextStudentCalled()
    }

    fun listenRouteSelected() {
        sdtSignalR.listenRouteSelected()
    }

    fun examEnded() {
        sdtSignalR.examEnded()
    }

    fun setLanguage(language: String) {
        sdtSignalR.setLanguage(language)
    }

    fun removeSignalRCallBack() {
        sdtSignalR.removeCallback("StudentsViewModel")
    }

    fun closeConnectionSignalR() {
        sdtSignalR.closeConnection()
    }

    private fun registerCallBack() {
        sdtSignalR.registerCallBack("StudentsViewModel", signalRCallback)
    }

    private var signalRCallback: SignalRCallback = object : SignalRCallback {
        override fun examEnded() {
            examEnded.value = ""
        }

        override fun languageSelectedBasedNationality(language: String) {
            languageSelectedBasedNationality.value = language
        }

        override fun routeSelected(routeId: Int) {
            selectedRoute.value = routeId
        }

        override fun roadData(roadData: String) {
            try {
                val turnsType = object : TypeToken<List<RoutesData>>() {}.type
                val roadWithStops =
                    Gson().fromJson<List<RoutesData>>(JSONArray(roadData).toString(), turnsType)
                roadDataWithStops.value = roadWithStops as ArrayList<RoutesData>
            } catch (e: Exception) {
                Log.d("error", "error in Road Data json")
            }
        }

        override fun nextStudent(studentData: String) {
            try {
                val student = Gson().fromJson(studentData, Student::class.java)
                studentObject.value = student
            } catch (e: Exception) {
                Log.d("error", "error in student json")
            }
        }


    }

    fun getRouteData() {
        studentUseCase("") { it ->
            it.either(::handleFailure) {
                RouteData.value = it
                setProcessStatus(RouteProcessStatus.SUCCESSFUL_DATA_COME.value!!)
                it
            }
        }
    }
}