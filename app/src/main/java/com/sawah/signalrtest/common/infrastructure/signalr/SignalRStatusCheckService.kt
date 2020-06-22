package com.sawah.signalrtest.common.infrastructure.signalr

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Handler
import android.os.PowerManager
import android.util.Log
import com.sawah.signalrtest.MainActivity
import com.sawah.vehicledashboardapp.R
import org.koin.android.ext.android.inject


class SignalRStatusCheckService : Service() {

    private var pm: PowerManager? = null
    private var wakeLock: PowerManager.WakeLock? = null
    private val signalRInstance: SdtSignalR by inject()


    override fun onBind(p0: Intent?) = null

    var handler: Handler = Handler()
    private val periodicUpdate: Runnable = object : Runnable {
        override fun run() {
            handler.postDelayed(
                this,
                1000
            )
            if (!signalRInstance.isConnected()) {
                Log.d("signalr", "try to get connection to signalr inside")
                signalRInstance.tryConnectEventHub {}
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handler.post(periodicUpdate)
        return START_STICKY
    }

    override fun onCreate() {
        val notification = createNotification()
        startForeground(1, notification)
        pm = getSystemService(Context.POWER_SERVICE) as PowerManager
        pm?.run {

            wakeLock =
                newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "SDT:wakeLogSignalRStatusCheckService")
            wakeLock?.acquire()
        }
    }

    private fun createNotification(): Notification {
        val notificationChannelId = "ENDLESS SERVICE CHANNEL"

        // depending on the Android API that we're dealing with we will have
        // to use a specific method to create the notification
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(
                notificationChannelId,
                "Location service Notification",
                NotificationManager.IMPORTANCE_HIGH
            ).let {
                it.description = "Location Service"
                it.enableLights(true)
                it.lightColor = Color.RED
                it.enableVibration(true)
                it.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
                it
            }
            notificationManager.createNotificationChannel(channel)
        }

        val pendingIntent: PendingIntent =
            Intent(this, MainActivity::class.java).let { notificationIntent ->
                PendingIntent.getActivity(this, 0, notificationIntent, 0)
            }


        val builder: Notification.Builder =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) Notification.Builder(
                this,
                notificationChannelId
            ) else Notification.Builder(this)

        return builder
            .setContentTitle("Health service Running")
            .setContentText("Health service Notification")
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setTicker("Ticker text")
            .setPriority(Notification.PRIORITY_HIGH) // for under android 26 compatibility
            .build()
    }

    override fun onDestroy() {
        try {
            wakeLock?.let {
                if (it.isHeld) {
                    it.release()
                }
            }
        } catch (e: Exception) {

        }
        super.onDestroy()
    }


}