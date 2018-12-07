package com.test.krishna.network

import com.test.krishna.models.Model
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("deliveries")
    fun getDeliveries(@Query("offset") offset: Int, @Query("limit") limit: Int): Observable<List<Model.Delivery>>

    companion object Factory {
        fun create(): Service {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
            okHttpClient.addInterceptor(loggingInterceptor)
            val retrofit = retrofit2.Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(URL_BASE)
                    .build()

            return retrofit.create(Service::class.java)
        }
    }
}