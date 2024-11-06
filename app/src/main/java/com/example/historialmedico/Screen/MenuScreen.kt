package com.example.historialmedico.Screen

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.ui.Modifier
import com.example.historialmedico.DataBase.FirebaseHistorialRepository


@Composable
fun MenuScreen(historialRepository: FirebaseHistorialRepository,
    onAddHistorialClick: () -> Unit ,
    onViewHistorialClick: () -> Unit) {

    val auth = FirebaseAuth.getInstance()
    val email = auth.currentUser?.email ?: ""
    val userName = email.substringBefore("@")
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Bienvenido $userName", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            historialRepository.getHistorial(
                onSuccess = { historialList ->
                    if (historialList.any { it.email == email }) {
                        showDialog = true
                    } else {
                        onAddHistorialClick()
                    }
                },
                onFailure = { /* Manejar error */ }
            )
        }) {
            Text(text = "Agregar Historial")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onViewHistorialClick) {
            Text(text = "Ver historial")
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Ya tienes un historial") },
                text = { Text("Ya tienes un historial registrado, si deseas modificarlo puedes hacerlo desde la opción de ver historial") },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog = false
                            onViewHistorialClick()
                        }
                    ) {
                        Text("Ver historial")
                    }
                }
            )
        }

    }

}




}



