package com.example.googlespeech.utils

import android.content.Context

fun saveToken(context: Context, token: String) {
    val sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
    sharedPreferences.edit().putString("access_token", token).apply()
}

fun getAccessToken(context: Context): String? {
    val sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString("access_token", null)
}
