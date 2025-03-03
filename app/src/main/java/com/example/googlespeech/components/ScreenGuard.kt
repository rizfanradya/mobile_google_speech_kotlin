package com.example.googlespeech.components

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.googlespeech.utils.Routes
import com.example.googlespeech.utils.getAccessToken

@Composable
fun AuthGuard(navController: NavController, content: @Composable () -> Unit) {
    val context = LocalContext.current
    val token = getAccessToken(context)
    val hasNavigated = remember { mutableStateOf(false) }

    if (!token.isNullOrEmpty() && !hasNavigated.value) {
        LaunchedEffect(Unit) {
            hasNavigated.value = true
            navController.navigate(Routes.HOME) {
                popUpTo(Routes.LOGIN) { inclusive = true }
            }
        }
    } else {
        content()
    }
}

@Composable
fun UserGuard(navController: NavController, content: @Composable () -> Unit) {
    val context = LocalContext.current
    val token = getAccessToken(context)
    val hasNavigated = remember { mutableStateOf(false) }

    if (token.isNullOrEmpty() && !hasNavigated.value) {
        LaunchedEffect(Unit) {
            hasNavigated.value = true
            navController.navigate(Routes.LOGIN) {
                popUpTo(Routes.HOME) { inclusive = true }
            }
        }
    } else {
        content()
    }
}