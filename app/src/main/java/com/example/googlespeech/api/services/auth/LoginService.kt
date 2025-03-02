package com.example.googlespeech.api.services.auth

import com.example.googlespeech.api.models.auth.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {
    @FormUrlEncoded
    @POST("token/mobile")
    fun androidLogin(
        @Field("username") username: String, @Field("password") password: String,
    ): Call<LoginResponse>
}