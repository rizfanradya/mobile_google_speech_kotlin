package com.example.googlespeech.utils

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController

fun logout(context: Context, navController: NavController) {
    val sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
    sharedPreferences.edit().remove("access_token").apply()
    Toast.makeText(context, "Logged out successfully", Toast.LENGTH_SHORT).show()
    navController.navigate("login") {
        popUpTo(0) { inclusive = true }
        launchSingleTop = true
    }
}

