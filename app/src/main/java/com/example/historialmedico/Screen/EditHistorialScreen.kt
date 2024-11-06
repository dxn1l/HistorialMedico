package com.example.historialmedico.Screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.historialmedico.DataBase.FirebaseHistorialRepository
import com.example.historialmedico.DataBase.historial

@Composable
fun EditHistorialScreen(navController: NavController, historial: historial) {
    val repository = FirebaseHistorialRepository()

    // Estados para los campos editables
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
        // Campos de entrada para editar el historial
        item { OutlinedTextField(value = nombre.value, onValueChange = { nombre.value = it }, label = { Text("Nombre") }) }
        item { OutlinedTextField(value = apellido.value, onValueChange = { apellido.value = it }, label = { Text("Apellido") }) }
        item { OutlinedTextField(value = edad.value, onValueChange = { edad.value = it }, label = { Text("Edad") }) }
        item { OutlinedTextField(value = peso.value, onValueChange = { peso.value = it }, label = { Text("Peso") }) }
        item { OutlinedTextField(value = altura.value, onValueChange = { altura.value = it }, label = { Text("Altura") }) }
        item { OutlinedTextField(value = telefono.value, onValueChange = { telefono.value = it }, label = { Text("Teléfono") }) }
        item { OutlinedTextField(value = correo.value, onValueChange = { correo.value = it }, label = { Text("Correo") }) }
        item { OutlinedTextField(value = sangre.value, onValueChange = { sangre.value = it }, label = { Text("Tipo de sangre") }) }
        item { OutlinedTextField(value = fechaUltimoExamen.value, onValueChange = { fechaUltimoExamen.value = it }, label = { Text("Fecha del último examen") }) }
        item { OutlinedTextField(value = enfermedades.value, onValueChange = { enfermedades.value = it }, label = { Text("Enfermedades") }) }
        item { OutlinedTextField(value = cirugias.value, onValueChange = { cirugias.value = it }, label = { Text("Cirugías") }) }
        item { OutlinedTextField(value = fechaCirugias.value, onValueChange = { fechaCirugias.value = it }, label = { Text("Fecha de las cirugías") }) }
        item { OutlinedTextField(value = medicamentos.value, onValueChange = { medicamentos.value = it }, label = { Text("Medicamentos") }) }
        item { OutlinedTextField(value = dosis.value, onValueChange = { dosis.value = it }, label = { Text("Dosis") }) }
        item { OutlinedTextField(value = usoMedicamentos.value, onValueChange = { usoMedicamentos.value = it }, label = { Text("Uso de medicamentos") }) }
        item { OutlinedTextField(value = alergias.value, onValueChange = { alergias.value = it }, label = { Text("Alergias") }) }
        item { OutlinedTextField(value = enfermedadesCronicas.value, onValueChange = { enfermedadesCronicas.value = it }, label = { Text("Enfermedades crónicas") }) }
        item { OutlinedTextField(value = antecedentesPersonales.value, onValueChange = { antecedentesPersonales.value = it }, label = { Text("Antecedentes personales") }) }

        item {
            Button(onClick = {
                Log.d("EditHistorialScreen", "ID del historial: ${historial.id}")  // Asegúrate de que el ID esté correcto

                // Eliminar el historial anterior
                repository.deleteHistorial(historial.id!!, onSuccess = {
                    Log.d("FirebaseHistorialRepository", "Historial eliminado exitosamente")

                    // Ahora agregar el historial actualizado
                    val updatedHistorial = historial.copy(
                        nombre = nombre.value,
                        apellido = apellido.value,
                        edad = edad.value.toInt(),
                        peso = peso.value.toInt(),
                        altura = altura.value.toDouble(),
                        telefono = telefono.value.toInt(),
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
                        Log.d("FirebaseHistorialRepository", "Historial actualizado con éxito")
                        navController.navigate("menu") {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }, onFailure = {
                        Log.e("FirebaseHistorialRepository", "Error al agregar historial actualizado", it)
                    })

                }, onFailure = { exception ->
                    Log.e("FirebaseHistorialRepository", "Error al eliminar historial anterior", exception)
                })
            }) {
                Text("Guardar cambios")
            }
        }
    }
}