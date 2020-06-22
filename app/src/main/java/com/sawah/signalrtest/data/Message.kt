package com.sawah.signalrtest.data

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.sawah.signalrtest.common.infrastructure.extensions.empty
import com.sawah.signalrtest.data.constants.Errors
import com.sawah.signalrtest.data.constants.MessageType
import com.sawah.signalrtest.data.constants.Successes

@Keep
class Message(
    @SerializedName("messageType") val messageType: MessageType = MessageType.SUCCESS,
    @SerializedName("errorDescription") val errorDescription: String = String.empty(),
    @SerializedName("errorCode") val errors: Errors = Errors.EMPTY_ERROR,
    @SerializedName("successDescription") val successDescription: String = String.empty(),
    @SerializedName("successCode") val successCode: Successes = Successes.EMPTY_SUCCESS
)



