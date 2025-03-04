package com.example.googlespeech.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.googlespeech.components.BottomNavBar
import com.example.googlespeech.utils.Routes

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentBackStackEntryState = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntryState.value?.destination?.route
    val hideBottomBar = currentRoute == Routes.LOGIN || currentRoute == Routes.REGISTER

    Scaffold(bottomBar = {
        if (!hideBottomBar) {
            BottomNavBar(navController)
        }
    }) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Routes.LOGIN) { LoginScreen(navController) }
            composable(Routes.REGISTER) { RegisterScreen(navController) }
            composable(Routes.HOME) { HomeScreen(navController) }
            composable(Routes.PROFILE) { ProfileScreen(navController) }
        }
    }
}
