package com.sawah.signalrtest.common.infrastructure.signalr

import android.annotation.SuppressLint
import android.app.Application
import android.os.AsyncTask
import android.util.Log
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import com.sawah.signalrtest.common.infrastructure.SignalRStatus
import kotlinx.coroutines.*


@Suppress("unused")
class SdtSignalR(
    private val ioScope: CoroutineScope, url: String, var application: Application,
    val main: CoroutineDispatcher = Dispatchers.Main
) {

    var hubUrl: String = url
    var activeHubConnection: HubConnection? = null

    //0 is connecting, 1 disconnected, 2 connected
    var signalRStatus = SignalRStatus.Disconnected.value

    private var callbackMap: HashMap<String, SignalRCallback> = HashMap()

    private var task1: HubConnectionTask? = null

    private val studentAuthenticated = "StudentAuthenticated"
    private val selectedRoute = "SetSelectedRoute"
    private val examEnded = "stop"
    private val languageNationality = "getLanguageByNationality"

    fun getConnectionId(): String {
        return try {
            if (activeHubConnection != null && activeHubConnection?.connectionId == null) {
                ""
            } else activeHubConnection!!.connectionId
        } catch (e: Exception) {
            ""
        }
    }

    fun registerCallBack(key: String, callback: SignalRCallback) {
        callbackMap[key] = callback
    }

    fun removeCallback(key: String) {
        try {
            callbackMap.remove(key)
        } catch (e: Exception) {

        }
    }


    // get student object and route data
    fun nextStudentCalled() {
        if (activeHubConnection != null) {
            activeHubConnection!!.on(
                studentAuthenticated, nextStudentcallBack,
                String::class.java,
                String::class.java
            )
        }
    }

    private val nextStudentcallBack = { student: String, roadData: String ->
        Log.d("signalr", "route stops come")
        ioScope.launch {
            withContext(main) {
                callbackMap.forEach {
                    it.value.nextStudent(student)
                    ; it.value.roadData(roadData)
                }
            }
        }
        Unit
    }

    fun listenRouteSelected() {
        if (activeHubConnection != null) {
            activeHubConnection!!.on(selectedRoute, selectRouteCallBack, String::class.java)
        }
    }

    private val selectRouteCallBack = { routeId: String ->
        Log.d("signalR", "route id recieved")
        ioScope.launch {
            withContext(main) {
                callbackMap.forEach {
                    it.value.routeSelected(routeId.toInt())
                }
            }
        }
        Unit
    }

    fun examEnded() {
        if (activeHubConnection != null) {
            activeHubConnection!!.on(examEnded, examEndedCallback)
        }
    }

    private val examEndedCallback = {
        Log.d("signalr", "examEnded ")
        ioScope.launch {
            withContext(main) {
                callbackMap.forEach {
                    it.value.examEnded()
                }
            }
        }
        Unit
    }

    private fun languageBasedOnNationality() {
        if (activeHubConnection != null) {
            activeHubConnection!!.on(languageNationality, languageCallback, String::class.java)
        }
    }

    private val languageCallback = { language: String ->
        Log.d("signalR", "language recieved")
        ioScope.launch {
            withContext(main) {
                callbackMap.forEach {
                    it.value.languageSelectedBasedNationality(language)
                }
            }
        }
        Unit
    }

    fun registerEvents() {
        examEnded()
        listenRouteSelected()
        nextStudentCalled()
        languageBasedOnNationality()
    }

    // added 18/5
    private fun removeALLHubConnectionInvocations() {
        try {
            if (activeHubConnection != null) {
                activeHubConnection!!.remove(examEnded)
                activeHubConnection!!.remove(selectedRoute)
                activeHubConnection!!.remove(studentAuthenticated)
                activeHubConnection!!.remove(languageNationality)
            }
        } catch (e: Exception) {

        }
    }


    fun closeConnection() {
        try {
            removeALLHubConnectionInvocations()
            if (activeHubConnection != null) {
                activeHubConnection!!.stop()
            }
            signalRStatus = SignalRStatus.Disconnected.value
            activeHubConnection = null
        } catch (e: Exception) {

        }
    }

    fun clearOnDestroy() {
        try {
            removeALLHubConnectionInvocations()
            if (activeHubConnection != null) {
                activeHubConnection!!.stop()
            }
            activeHubConnection = null
        } catch (e: Exception) {

        }
    }

    fun setLanguage(language: String) {
        sendDataToServer {
            activeHubConnection!!.invoke(language)
        }
    }

    private fun sendDataToServer(invokeCallBack: () -> Unit) {
        try {

            if (isConnected()) {
                invokeCallBack()
            }
        } catch (e: Exception) {
            Log.d(
                "signalr",
                e.message + "invoke method cannot be called if the connection is not active"
            )
        }

    }

    fun tryConnectEventHub(invokeCallBack: () -> Unit) {
        try {
            if (task1 == null) {
                task1 = HubConnectionTask(invokeCallBack)
                task1!!.execute()
            } else {
                when {
                    task1!!.status == AsyncTask.Status.PENDING -> task1!!.execute()
                    task1!!.status == AsyncTask.Status.FINISHED -> {
                        task1 = HubConnectionTask(invokeCallBack)
                        task1!!.execute()
                    }
                    task1!!.status == AsyncTask.Status.RUNNING -> return
                }
            }
        } catch (e: Exception) {
            Log.d("error", "error in thread async")
        }
    }

    @SuppressLint("StaticFieldLeak")
    internal inner class HubConnectionTask(private var callBack: () -> Unit) :
        AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg args: Void): Void? {
            try {
                activeHubConnection = HubConnectionBuilder.create(hubUrl).build()
                while (signalRStatus != SignalRStatus.Connected.value) {
                    if (isCancelled) {
                        break
                    }
                    signalRStatus = SignalRStatus.Connecting.value
                    try {
                        activeHubConnection!!.start().blockingAwait()
                        signalRStatus = SignalRStatus.Connected.value
                        registerEvents()
                        callBack.invoke()
                    } catch (e: Exception) {
                        Log.d("signalr", "Failed To Connected")
                        signalRStatus = SignalRStatus.Disconnected.value
                    }
                }
            } catch (e: Exception) {
                Log.d("signalr", "error in doInBackground")
            }
            return null
        }
    }

    fun isConnected(): Boolean {
        return signalRStatus == SignalRStatus.Connected.value
    }


}

interface SignalRCallback {
    fun nextStudent(studentObject: String)
    fun roadData(roadData: String)
    fun routeSelected(routeId: Int)
    fun examEnded()
    fun languageSelectedBasedNationality(language: String)
}


