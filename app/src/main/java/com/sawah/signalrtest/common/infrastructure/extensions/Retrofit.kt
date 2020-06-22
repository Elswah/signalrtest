package com.sawah.signalrtest.common.infrastructure.extensions

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.stream.MalformedJsonException
import com.sawah.signalrtest.common.infrastructure.exception.Failure
import com.sawah.signalrtest.common.infrastructure.functional.Either
import com.sawah.signalrtest.data.Message

import retrofit2.Call
import java.net.ConnectException
import java.net.UnknownHostException


/**
 * Takes in a transform lambda to return a modified version of the responsex
 */
@Suppress("unused")
fun <T, R> Call<T>.requestTransformBlocking(transform: (T) -> R): Either<Failure, R> {
    return try {
        val response = execute()
        when (response.isSuccessful) {
            true -> Either.Right(transform(response.body()!!))
            false -> when (response.code()) {
                401 -> Either.Left(Failure.AuthError)
                400 -> Either.Left(Failure.BadRequest)
                404 -> Either.Left(Failure.NotFound)
                415 -> Either.Left(Failure.UnSupportedMediaType)
                500 -> {
                    val remoteErrorMessage: Message =
                        Gson().fromJson(response.errorBody()!!.charStream(), Message::class.java)
                    Either.Left(Failure.InternalServerError.apply { message = remoteErrorMessage })
                }
                else -> Either.Left(Failure.ServerError)
            }
        }
    } catch (exception: Throwable) {
        when (exception) {
            is SecurityException -> Either.Left(Failure.AndroidError)
            is UnknownHostException -> Either.Left(Failure.NetworkConnection)
            is MalformedJsonException -> Either.Left(Failure.MalFormedJson)
            is IllegalStateException -> Either.Left(Failure.IllegalStateException)
            is JsonSyntaxException -> Either.Left(Failure.JsonSyntaxException)
            else -> Either.Left(Failure.ServerError)
        }
    }
}

@Suppress("unused")
fun <T> Call<T>.requestBlocking(default: T): Either<Failure, T> {
    return try {
        val response = execute()
        when (response.isSuccessful) {
            true -> Either.Right((response.body() ?: default))
            false -> when (response.code()) {
                401 -> Either.Left(Failure.AuthError)
                400 -> Either.Left(Failure.BadRequest)
                404 -> Either.Left(Failure.NotFound)
                415 -> Either.Left(Failure.UnSupportedMediaType)
                500 -> {
                    val remoteErrorMessage: Message =
                        Gson().fromJson(response.errorBody()!!.charStream(), Message::class.java)
                    Either.Left(Failure.InternalServerError.apply { message = remoteErrorMessage })
                }
                else -> Either.Left(Failure.ServerError)
            }
        }
    } catch (exception: Throwable) {
        when (exception) {
            is SecurityException -> Either.Left(Failure.AndroidError)
            is UnknownHostException -> Either.Left(Failure.NetworkConnection)
            is MalformedJsonException -> Either.Left(Failure.MalFormedJson)
            is IllegalStateException -> Either.Left(Failure.IllegalStateException)
            is JsonSyntaxException -> Either.Left(Failure.JsonSyntaxException)
            else -> Either.Left(Failure.ServerError)
        }
    }
}

fun <T> Call<T>.requestBlocking(): Either<Failure, T> {
    return try {
        val response = execute()
        when (response.isSuccessful) {
            true -> Either.Right((response.body()!!))
            false -> when (response.code()) {
                401 -> Either.Left(Failure.AuthError)
                400 -> Either.Left(Failure.BadRequest)
                404 -> Either.Left(Failure.NotFound)
                415 -> Either.Left(Failure.UnSupportedMediaType)
                500 -> {
                    val remoteErrorMessage: Message =
                        Gson().fromJson(response.errorBody()!!.charStream(), Message::class.java)
                    Either.Left(Failure.InternalServerError.apply { message = remoteErrorMessage })
                }
                else -> Either.Left(Failure.ServerError)
            }
        }
    } catch (exception: Throwable) {
        when (exception) {
            is SecurityException -> Either.Left(Failure.AndroidError)
            is ConnectException,
            is UnknownHostException -> Either.Left(Failure.NetworkConnection)
            is MalformedJsonException -> Either.Left(Failure.MalFormedJson)
            is IllegalStateException -> Either.Left(Failure.IllegalStateException)
            is JsonSyntaxException -> Either.Left(Failure.JsonSyntaxException)
            else -> Either.Left(Failure.ServerError)
        }
    }
}





