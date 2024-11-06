package com.example.historialmedico.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.historialmedico.DataBase.FirebaseHistorialRepository
import com.google.firebase.auth.FirebaseAuth

@Composable
fun MenuScreen(
    historialRepository: FirebaseHistorialRepository,
    onAddHistorialClick: () -> Unit,
    onViewHistorialClick: () -> Unit
) {
    val firebaseUser = FirebaseAuth.getInstance().currentUser
    val userId = firebaseUser?.uid ?: "" // Get user ID or empty string if null
    val email = firebaseUser?.email ?: "" // Get email or empty string if null
    val userName = if (email.isNotEmpty()) email.substringBefore("@") else ""
    var showDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var hasHistorial by remember { mutableStateOf(false) }

    LaunchedEffect(userId) {
        if (userId.isNotEmpty()) {
            try {
                historialRepository.getHistorial(
                    onSuccess = { historialList ->
                        hasHistorial = historialList.any { it.userId == userId }
                    },
                    onFailure = { error ->
                        errorMessage = "Error al obtener historial: ${error.message}"
                    }
                )
            } catch (e: Exception) {
                errorMessage = "Error inesperado: ${e.message}"
            }
        }else{
            errorMessage = "Usuario no autenticado. Inténtalo de nuevo."
        }
    }

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
            if (hasHistorial) {
                showDialog = true
            } else {
                onAddHistorialClick()
                hasHistorial = true
            }
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

        errorMessage?.let { message ->
            AlertDialog(
                onDismissRequest = { errorMessage = null },
                title = { Text("Error") },
                text = { Text(message) },
                confirmButton = {
                    Button(
                        onClick = { errorMessage = null }) {
                        Text("Aceptar")
                    }
                }
            )
        }
    }
}
