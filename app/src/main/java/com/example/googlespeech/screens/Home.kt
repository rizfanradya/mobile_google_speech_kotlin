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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.googlespeech.components.UserGuard
import com.example.googlespeech.utils.getAccessToken


@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    UserGuard(navController) {
        val context = LocalContext.current

        Column(
            modifier = modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.systemBars),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Home Screen", fontWeight = FontWeight.SemiBold, fontSize = 30.sp
            )
            Text(
                text = "TOKEN : ${getAccessToken(context)}",
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp
            )
        }
    }
}