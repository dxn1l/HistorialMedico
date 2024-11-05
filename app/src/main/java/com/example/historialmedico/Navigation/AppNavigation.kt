package com.example.historialmedico.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.historialmedico.Screen.LoginScreen

@Composable
fun AppNavigation() {

var currentScreen by remember { mutableStateOf(Screen.Login) }

when(currentScreen) {
    Screen.Login -> LoginScreen(onLoginSuccess = { currentScreen = Screen.Menu })
    Screen.Menu -> {}
}

}

enum class Screen{

Login,
Menu

}