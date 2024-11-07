package com.example.historialmedico.Screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.historialmedico.DataBase.FirebaseHistorialRepository
import com.example.historialmedico.DataBase.historial

@Composable
fun ViewHistorialScreen(
    historialRepository: FirebaseHistorialRepository,
    onBack: () -> Unit,
    onEditHistorialClick: (historial) -> Unit
) {
    val historialList = remember { mutableStateListOf<historial>() }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        historialRepository.getHistorial(
            onSuccess = { 
                historialList.clear()
                historialList.addAll(it)
                Log.d("ViewHistorialScreen", "Historial list updated: $historialList")
            },
            onFailure = {
                errorMessage = "Error al obtener historial: ${it.message}"
                Log.e("ViewHistorialScreen", errorMessage, it)
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(historialList) { historial ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(text = "Nombre: ${historial.nombre}", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Apellido: ${historial.apellido}", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Edad: ${historial.edad} años", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Peso: ${historial.peso} kg", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Altura: ${historial.altura} m", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Teléfono: +34 ${historial.telefono}", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Correo: ${historial.correo}", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Tipo de sangre: ${historial.sangre}", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Fecha del último examen: ${historial.fechaUltimoExamen}", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Enfermedades: ${historial.enfermedades}", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Cirugías: ${historial.cirugias}", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Fecha de las cirugías: ${historial.fechaCirugias}", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Medicamentos: ${historial.medicamentos}", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Dosis: ${historial.dosis}", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Uso de medicamentos: ${historial.usoMedicamentos}", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Alergias: ${historial.alergias}", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Enfermedades crónicas: ${historial.enfermedadesCronicas}", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Antecedentes personales: ${historial.antecedentesPersonales}", style = MaterialTheme.typography.bodyLarge)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        // If there is an error, show it
        errorMessage?.let {
            Text(text = it, style = MaterialTheme.typography.bodyLarge)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { onBack() }) {
                Text(text = "Volver al menú")
            }
            Button(onClick = { onEditHistorialClick(historialList.first()) }) {
                Text(text = "Modificar Historial")
            }
        }
    }
}