package com.example.historialmedico.Screen

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier


@Composable
fun MenuScreen(onAddHistorialClick: () -> Unit , onViewHistorialClick: () -> Unit) {

    val auth = FirebaseAuth.getInstance()
    val email = auth.currentUser?.email ?: ""
    val userName = email.substringBefore("@")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Bienvenido $userName", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onAddHistorialClick) {
            Text(text = "Agregar Historial")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onViewHistorialClick) {
            Text(text = "Ver historial")
        }





    }




}



