package com.example.googlespeech.api.models.user

import com.google.gson.annotations.SerializedName

data class CreateUserModel(
    val email: String,
    val password: String,
    @SerializedName("full_name") val fullName: String,
)
