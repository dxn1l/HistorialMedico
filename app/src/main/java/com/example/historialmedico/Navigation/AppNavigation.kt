package com.example.historialmedico.Navigation

import ViewHistorialScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.historialmedico.DataBase.FirebaseHistorialRepository
import com.example.historialmedico.DataBase.historial
import com.example.historialmedico.Screen.AddHistorialScreen
import com.example.historialmedico.Screen.LoginScreen
import com.example.historialmedico.Screen.MenuScreen
import com.example.historialmedico.Screen.EditHistorialScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    val historialRepository = FirebaseHistorialRepository()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(onLoginSuccess = { navController.navigate("menu") })
        }
        composable("menu") {
            MenuScreen(
                onAddHistorialClick = { navController.navigate("addHistorial") },
                onViewHistorialClick = { navController.navigate("viewHistorial") }
            )
        }
        composable("addHistorial") {
            AddHistorialScreen(
                historialRepository = historialRepository,
                onHistorialAdded = { navController.navigate("menu") }
            )
        }
        composable("viewHistorial") {
            ViewHistorialScreen(
                historialRepository = historialRepository,
                onBack = { navController.navigate("menu") },
                onEditHistorialClick = { historial ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("historial", historial)
                    navController.navigate("editHistorial")
                }
            )
        }
        composable("editHistorial") {
            val historial = navController.previousBackStackEntry?.savedStateHandle?.get<historial>("historial")
            historial?.let {
                EditHistorialScreen(
                    navController = navController,
                    historial = it
                )
            }
        }
    }
}