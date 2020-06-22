package com.sawah.signalrtest.data.source.remote

import com.sawah.signalrtest.utils.NullOnEmptyConverterFactory
import com.sawah.vehicledashboardapp.BuildConfig
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val healthNetwork = module {
    single {
        val connectTimeout = 2 // 2s
        val readTimeout = 2 // 2s

        val builder = OkHttpClient().newBuilder()
            .connectTimeout(connectTimeout.toLong(), TimeUnit.SECONDS)
            .readTimeout(readTimeout.toLong(), TimeUnit.SECONDS)
        // handle stackOverFlow error
        if (builder.interceptors().isNotEmpty()) {
            builder.interceptors().clear()
        }
        /*if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            builder.addNetworkInterceptor(httpLoggingInterceptor)
        }*/
        builder.build()
    }
    single<Retrofit>(named("health")) {
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
