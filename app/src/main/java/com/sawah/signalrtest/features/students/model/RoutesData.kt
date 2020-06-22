package com.sawah.signalrtest.features.students.model

import com.google.gson.annotations.SerializedName

data class RoutesData(
    @SerializedName("routeId") val routeId: String,
    @SerializedName("isActive") val isActive: Boolean,
    @SerializedName("stationCenterId") val stationCenterId: Int,
    @SerializedName("stationCenter") val stationCenter: String,
    @SerializedName("boards") val boards: List<String>,
    @SerializedName("routeStops") val routeStops: List<Stop>,
    @SerializedName("imageURL") val imageURL: String,
    @SerializedName("id") val id: Int
)