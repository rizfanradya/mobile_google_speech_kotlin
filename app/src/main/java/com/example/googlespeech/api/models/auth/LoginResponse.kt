package com.example.googlespeech.api.models.auth

data class LoginResponse(
    val id: Int,
    val accessToken: String,
    val status: Boolean,
    val role: String,
    val detail: String,
)
