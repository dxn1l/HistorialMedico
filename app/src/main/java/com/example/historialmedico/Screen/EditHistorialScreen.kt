package com.example.historialmedico.Screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.historialmedico.DataBase.FirebaseHistorialRepository
import com.example.historialmedico.DataBase.historial

@Composable
fun EditHistorialScreen(navController: NavController, historialId: String) {
    val repository = FirebaseHistorialRepository()
    var historial by remember { mutableStateOf<historial?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(historialId) {
        repository.getHistorial(
            onSuccess = { historialList ->
                historial = historialList.find { it.id == historialId }
            },
            onFailure = { error ->
                errorMessage = "Error al obtener historial: ${error.message}"
            }
        )
    }

    if (historial != null) {
        EditHistorialScreenContent(navController = navController, historial = historial!!, repository = repository)
    } else {
        errorMessage?.let { Text(text = it, color = MaterialTheme.colorScheme.error) }
    }
}

@Composable
fun EditHistorialScreenContent(navController: NavController, historial: historial, repository: FirebaseHistorialRepository) {
    // States for editable fields
    val nombre = remember { mutableStateOf(historial.nombre) }
    val apellido = remember { mutableStateOf(historial.apellido) }
    val edad = remember { mutableStateOf(historial.edad.toString()) }
    val peso = remember { mutableStateOf(historial.peso.toString()) }
    val altura = remember { mutableStateOf(historial.altura.toString()) }
    val telefono = remember { mutableStateOf(historial.telefono.toString()) }
    val correo = remember { mutableStateOf(historial.correo) }
    val sangre = remember { mutableStateOf(historial.sangre) }
    val fechaUltimoExamen = remember { mutableStateOf(historial.fechaUltimoExamen) }
    val enfermedades = remember { mutableStateOf(historial.enfermedades) }
    val cirugias = remember { mutableStateOf(historial.cirugias) }
    val fechaCirugias = remember { mutableStateOf(historial.fechaCirugias) }
    val medicamentos = remember { mutableStateOf(historial.medicamentos) }
    val dosis = remember { mutableStateOf(historial.dosis) }
    val usoMedicamentos = remember { mutableStateOf(historial.usoMedicamentos) }
    val alergias = remember { mutableStateOf(historial.alergias) }
    val enfermedadesCronicas = remember { mutableStateOf(historial.enfermedadesCronicas) }
    val antecedentesPersonales = remember { mutableStateOf(historial.antecedentesPersonales) }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item {
            OutlinedTextField(
                value = nombre.value,
                onValueChange = { nombre.value = it },
                label = { Text("Nombre") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = apellido.value,
                onValueChange = { apellido.value = it },
                label = { Text("Apellido") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = edad.value,
                onValueChange = { edad.value = it },
                label = { Text("Edad") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = peso.value,
                onValueChange = { peso.value = it },
                label = { Text("Peso") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = altura.value,
                onValueChange = { altura.value = it },
                label = { Text("Altura") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = telefono.value,
                onValueChange = { telefono.value = it },
                label = { Text("Teléfono") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = correo.value,
                onValueChange = { correo.value = it },
                label = { Text("Correo") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = sangre.value,
                onValueChange = { sangre.value = it },
                label = { Text("Tipo de sangre") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = fechaUltimoExamen.value,
                onValueChange = { fechaUltimoExamen.value = it },
                label = { Text("Fecha del último examen") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = enfermedades.value,
                onValueChange = { enfermedades.value = it },
                label = { Text("Enfermedades") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = cirugias.value,
                onValueChange = { cirugias.value = it },
                label = { Text("Cirugías") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = fechaCirugias.value,
                onValueChange = { fechaCirugias.value = it },
                label = { Text("Fecha de las cirugías") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = medicamentos.value,
                onValueChange = { medicamentos.value = it },
                label = { Text("Medicamentos") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = dosis.value,
                onValueChange = { dosis.value = it },
                label = { Text("Dosis") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = usoMedicamentos.value,
                onValueChange = { usoMedicamentos.value = it },
                label = { Text("Uso de medicamentos") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = alergias.value,
                onValueChange = { alergias.value = it },
                label = { Text("Alergias") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = enfermedadesCronicas.value,
                onValueChange = { enfermedadesCronicas.value = it },
                label = { Text("Enfermedades crónicas") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = antecedentesPersonales.value,
                onValueChange = { antecedentesPersonales.value = it },
                label = { Text("Antecedentes personales") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                val updatedHistorial = historial.copy(
                    nombre = nombre.value,
                    apellido = apellido.value,
                    edad = edad.value.toIntOrNull() ?: 0,
                    peso = peso.value.toIntOrNull() ?: 0,
                    altura = altura.value.toDoubleOrNull() ?: 0.0,
                    telefono = telefono.value.toIntOrNull() ?: 0,
                    correo = correo.value,
                    sangre = sangre.value,
                    fechaUltimoExamen = fechaUltimoExamen.value,
                    enfermedades = enfermedades.value,
                    cirugias = cirugias.value,
                    fechaCirugias = fechaCirugias.value,
                    medicamentos = medicamentos.value,
                    dosis = dosis.value,
                    usoMedicamentos = usoMedicamentos.value,
                    alergias = alergias.value,
                    enfermedadesCronicas = enfermedadesCronicas.value,
                    antecedentesPersonales = antecedentesPersonales.value
                )
                repository.addHistorial(updatedHistorial, onSuccess = {
                    navController.navigate("menu")
                }, onFailure = {
                    // Handle failure
                })
            }) {
                Text("Guardar cambios")
            }
        }
    }
}