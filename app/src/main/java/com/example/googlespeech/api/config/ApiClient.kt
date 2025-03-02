package com.example.googlespeech.api.config

import com.example.googlespeech.api.services.auth.LoginService
import com.example.googlespeech.api.services.user.CreateUserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "http://89.116.20.146:8004/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val loginService: LoginService by lazy {
        retrofit.create(LoginService::class.java)
    }

    val registerService: CreateUserService by lazy {
        retrofit.create(CreateUserService::class.java)
    }
}