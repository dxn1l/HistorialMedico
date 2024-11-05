package com.example.historialmedico

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.historialmedico.Navigation.AppNavigation
import com.example.historialmedico.ui.theme.HistorialMedicoTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            HistorialMedicoTheme {
                AppNavigation()
            }
        }
    }
}

