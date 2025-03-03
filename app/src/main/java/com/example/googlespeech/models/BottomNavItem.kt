package com.example.googlespeech.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.googlespeech.utils.Routes

sealed class BottomNavItem(val title: String, val icon: ImageVector, val route: String) {
    object Home : BottomNavItem(Routes.HOME, Icons.Filled.Home, Routes.HOME)
    object Profile : BottomNavItem(Routes.PROFILE, Icons.Filled.Person, Routes.PROFILE)
}
