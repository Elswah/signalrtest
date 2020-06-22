package com.sawah.signalrtest.utils

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.content.SharedPreferences
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class AppUtils {
    companion object {

        @Suppress("deprecation")
        fun isMyServiceRunning(serviceClass: Class<*>, context: Context): Boolean {
            val manager =
                context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager?
            for (service in manager!!.getRunningServices(Int.MAX_VALUE)) {
                if (serviceClass.name == service.service.className) {
                    return true
                }
            }
            return false
        }


        @Suppress("unused")
        fun isOnline(context: Context): Boolean {
            val sharedPref: SharedPreferences =
                context.getSharedPreferences(Contract.PREF_NAME, Contract.PRIVATE_MODE)
            return sharedPref.edit().putBoolean("isOffline", false).commit()
        }


        @SuppressLint("SimpleDateFormat")
        fun parseDate(
            dateTime: String,
            inputPattern: String,
            outputPattern: String
        ): String? {
            val inputFormat = SimpleDateFormat(inputPattern)
            val outputFormat = SimpleDateFormat(outputPattern)
            val date: Date?
            var str: String? = dateTime
            try {
                date = inputFormat.parse(dateTime)
                str = outputFormat.format(date!!)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return str
        }

        /*@SuppressLint("SimpleDateFormat")
        @Suppress("unused")
        fun getRelativeDateTime(createdOn: String): String {

            var dateInProperFormat = createdOn
            try {
                val prettyTime = PrettyTime(Locale.getDefault())
                val sdf = SimpleDateFormat("yyyy-MM-ddTHH:mm:ss.SSS")
                val date = sdf.parse(createdOn)
                val cal = Calendar.getInstance()
                cal.time = date!!
                dateInProperFormat = prettyTime.format(cal)

            } catch (e: Exception) {

                e.printStackTrace()
            }

            return dateInProperFormat
        }*/

        @Suppress("unused")
        fun hideKeyboard(activity: AppCompatActivity) {
            val focus = activity.currentFocus
            if (focus != null) {
                val keyboard =
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                keyboard.hideSoftInputFromWindow(focus.windowToken, 0)
            }
        }
    }

}