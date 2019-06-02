package com.eisais.app.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Url {
    companion object {
        const val BASE_URL = "https://school-site-api.herokuapp.com/api/v1/"
        const val URL = "api/v1/post/"
    }
}

class Connect {
    companion object {
        private fun getRetrofit(Url: String): Retrofit {
            return Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(Url)
                .build()
        }

        fun getApiData(): Retrofit {
            val retrofitApi = getRetrofit(Url.BASE_URL)

            return retrofitApi
        }

        fun callApi(): ApiInterface {
            val retrofitCall = getApiData()

            return retrofitCall.create(ApiInterface::class.java)
        }
    }
}