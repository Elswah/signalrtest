package com.sawah.signalrtest.features.students.model

import com.google.gson.annotations.SerializedName

data class Stop(
    @SerializedName("sequenceNumber") val sequenceNumber: Int,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("isActive") val isActive: Boolean,
    @SerializedName("id") val id: Int
)