package com.example.historialmedico.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.historialmedico.Screen.AddHistorialScreen
import com.example.historialmedico.Screen.LoginScreen
import com.example.historialmedico.Screen.MenuScreen
import com.example.historialmedico.Screen.ViewHistorialScreen

@Composable
fun AppNavigation() {

var currentScreen by remember { mutableStateOf(Screen.Login) }

when(currentScreen) {
    Screen.Login -> LoginScreen(onLoginSuccess = { currentScreen = Screen.Menu })
    Screen.Menu -> MenuScreen(
        onAddHistorialClick = { currentScreen = Screen.AddHistorial },
        onViewHistorialClick = { currentScreen = Screen.ViewHistorial })

    Screen.AddHistorial -> AddHistorialScreen()
    Screen.ViewHistorial -> ViewHistorialScreen()
}

}

enum class Screen{

Login,
Menu,
AddHistorial,
ViewHistorial

}