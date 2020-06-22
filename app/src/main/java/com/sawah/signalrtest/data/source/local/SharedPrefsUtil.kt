package com.sawah.signalrtest.data.source.local

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sawah.signalrtest.features.students.model.Stop


class SharedPrefsUtil(private var sharedPreferences: SharedPreferences) {

    private fun getSharedPrefs(): SharedPreferences {
        return sharedPreferences
    }

    fun save(key: String, value: String) {

        val editor = getSharedPrefs().edit()
        editor.putString(key, value)
        editor.apply()

    }

    fun save(key: String, value: Int) {

        val editor = getSharedPrefs().edit()
        editor.putInt(key, value)
        editor.apply()

    }

    fun save(key: String, `val`: Long) {

        val editor = getSharedPrefs().edit()
        editor.putLong(key, `val`)
        editor.apply()

    }

    fun save(key: String, `val`: Boolean) {


        val editor = getSharedPrefs().edit()
        editor.putBoolean(key, `val`)
        editor.apply()

    }

    @Suppress("unused")
    fun getBoolData(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    @Suppress("unused")
    fun getBoolData(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun getStringData(key: String): String? {
        val data = sharedPreferences.getString(key, null)
        return if (data != null && data.trim { it <= ' ' }.isEmpty()) {

            null
        } else data

    }

    @Suppress("unused")
    fun getIntData(key: String): Int {
        return sharedPreferences.getInt(key, 0)

    }

    @Suppress("unused")
    fun getLongData(key: String): Long {
        return sharedPreferences.getLong(key, 1)

    }

    fun saveArrayList(list: ArrayList<Stop>, key: String) {
        try {
            val editor = sharedPreferences.edit()
            val gson = Gson()
            val json = gson.toJson(list)
            editor.putString(key, json)
            editor.apply()
        } catch (e: Exception) {
            Log.d("error", "error save in sharedPref")
        }
    }

    fun getArrayList(key: String): ArrayList<Stop>? {
        try {
            val gson = Gson()
            val json = sharedPreferences.getString(key, null)
            val type = object : TypeToken<ArrayList<Stop>>() {}.type
            return gson.fromJson<ArrayList<Stop>>(json, type)
        } catch (e: Exception) {
            Log.d("error", "error get stop from sharedPref")
        }
        return null
    }

    fun removeItem(key: String) {
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()

    }

    fun clearShared() {
        sharedPreferences.edit().clear().apply()
    }

    fun isContain(key: String): Boolean {
        return sharedPreferences.contains(key)
    }


}