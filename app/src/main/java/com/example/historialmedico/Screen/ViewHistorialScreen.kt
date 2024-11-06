package com.example.historialmedico.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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

    LaunchedEffect(Unit) {
        historialRepository.getHistorial(
            onSuccess = { historialList.clear(); historialList.addAll(it) },
            onFailure = { }
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
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
                    Text(text = "Teléfono:  +34 ${historial.telefono}", style = MaterialTheme.typography.bodyLarge)
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { onBack() }) {
                    Text(text = "Volver al menú")
                }
                Button(onClick = { onEditHistorialClick(historial) }) {
                    Text(text = "Modificar Historial")
                }
            }
        }
    }
}