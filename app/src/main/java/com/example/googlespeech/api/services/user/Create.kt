package com.example.googlespeech.api.services.user

import com.example.googlespeech.api.models.user.CreateUserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CreateUserService {
    @Headers("Content-Type: application/json")
    @POST("user/_create")
    fun registerUser(@Body request: CreateUserModel): Call<Void>
}