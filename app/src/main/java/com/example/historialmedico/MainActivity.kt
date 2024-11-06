// MainActivity.kt
package com.example.historialmedico

import AppNavigation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.historialmedico.ui.theme.HistorialMedicoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HistorialMedicoTheme {
                val navController = rememberNavController()
                AppNavigation(navController = navController)
            }
        }
    }
}