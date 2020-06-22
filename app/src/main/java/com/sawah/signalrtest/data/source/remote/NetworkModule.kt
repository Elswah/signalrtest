package com.sawah.signalrtest.data.source.remote

import com.sawah.signalrtest.utils.NullOnEmptyConverterFactory
import com.sawah.vehicledashboardapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {

    single {
        val connectTimeout = 2 // 60s
        val readTimeout = 2 // 60s

        val builder = OkHttpClient().newBuilder()
            .connectTimeout(connectTimeout.toLong(), TimeUnit.SECONDS)
            .readTimeout(readTimeout.toLong(), TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            builder.addNetworkInterceptor(httpLoggingInterceptor)
        }
        builder.build()
    }

    single<Retrofit> {
        val retrofitBuilder = Retrofit.Builder()
        retrofitBuilder.apply {
            client(get())
            baseUrl(BuildConfig.END_POINT)
            addConverterFactory(NullOnEmptyConverterFactory())
            addConverterFactory(GsonConverterFactory.create())
            //addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        }.build()
    }
}