package com.example.candyspace

import com.example.candyspace.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val myApi: RetrofitApi by lazy {
        retrofit.create(RetrofitApi::class.java)
    }

}