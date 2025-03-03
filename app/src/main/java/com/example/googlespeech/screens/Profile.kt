package com.example.googlespeech.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.googlespeech.components.UserGuard

@Composable
fun ProfileScreen(navController: NavController, modifier: Modifier = Modifier) {
    UserGuard(navController) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.systemBars),
            verticalArrangement = Arrangement.Top
        ) {

            Text(
                text = "Profile Screen", fontWeight = FontWeight.SemiBold, fontSize = 30.sp
            )
        }
    }
}