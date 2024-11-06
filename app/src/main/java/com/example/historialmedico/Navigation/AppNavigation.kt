package com.example.historialmedico.Navigation

import ViewHistorialScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.historialmedico.DataBase.FirebaseHistorialRepository
import com.example.historialmedico.Screen.AddHistorialScreen
import com.example.historialmedico.Screen.LoginScreen
import com.example.historialmedico.Screen.MenuScreen

@Composable
fun AppNavigation() {

val historialRepository = FirebaseHistorialRepository()
var currentScreen by remember { mutableStateOf(Screen.Login) }

when(currentScreen) {
    Screen.Login -> LoginScreen(onLoginSuccess = { currentScreen = Screen.Menu })
    Screen.Menu -> MenuScreen(
        historialRepository = historialRepository,
        onAddHistorialClick = { currentScreen = Screen.AddHistorial },
        onViewHistorialClick = { currentScreen = Screen.ViewHistorial })

    Screen.AddHistorial -> AddHistorialScreen( historialRepository = historialRepository , onHistorialAdded = { currentScreen = Screen.Menu })
    Screen.ViewHistorial -> ViewHistorialScreen( historialRepository = historialRepository , onBack = { currentScreen = Screen.Menu })
}

}

enum class Screen{

Login,
Menu,
AddHistorial,
ViewHistorial

}