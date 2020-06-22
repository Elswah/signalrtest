package com.sawah.signalrtest.features.students.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class Student(
    @SerializedName("id") var id: String,
    @SerializedName("trafficFileNumber") var trafficFileNumber: String,
    @SerializedName("arabicName") var arabicName: String,
    @SerializedName("englishName") var englishName: String,
    @SerializedName("testId") var testId: Int,
    @SerializedName("state") var state: Int,
    @SerializedName("studentOrder") var studentOrder: String,
    @SerializedName("emiratesIdNo") var emiratesIdNo: String
) : Serializable