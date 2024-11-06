package com.example.historialmedico.Screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import com.example.historialmedico.DataBase.FirebaseHistorialRepository
import com.example.historialmedico.DataBase.historial
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

@Composable
fun AddHistorialScreen(

    historialRepository: FirebaseHistorialRepository,
    onHistorialAdded: () -> Unit


) {

    var id by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var sangre by remember { mutableStateOf("") }
    var fechaUltimoExamen by remember { mutableStateOf("") }
    var enfermedades by remember { mutableStateOf("") }
    var cirugias by remember { mutableStateOf("") }
    var fechaCirugias by remember { mutableStateOf("") }
    var medicamentos by remember { mutableStateOf("") }
    var dosis by remember { mutableStateOf("") }
    var usoMedicamentos by remember { mutableStateOf("") }
    var alergias by remember { mutableStateOf("") }
    var enfermedadesCronicas by remember { mutableStateOf("") }
    var antecedentesPersonales by remember { mutableStateOf("") }
    var showDialogEmptylane by remember { mutableStateOf(false) }
    val bloodTypes = listOf("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")
    var expanded by remember { mutableStateOf(false) }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(8.dp)
            ) {


                Column(

                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                ) {

                    OutlinedTextField(
                        value = id,
                        onValueChange = { id = it },
                        label = { Text("ID") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = apellido,
                        onValueChange = { apellido = it },
                        label = { Text("Apellido") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = edad,
                        onValueChange = { edad = it },
                        label = { Text("Edad") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = peso,
                        onValueChange = { peso = it },
                        label = { Text("Peso") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = altura,
                        onValueChange = { altura = it },
                        label = { Text("Altura") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = telefono,
                        onValueChange = { telefono = it },
                        label = { Text("Telefono") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = correo,
                        onValueChange = { correo = it },
                        label = { Text("Correo") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))


                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        bloodTypes.forEach { bloodType ->
                            DropdownMenuItem(
                                text = { Text(bloodType) },
                                onClick = {
                                    sangre = bloodType
                                    expanded = false
                                })
                        }
                    }
                    OutlinedTextField(
                        value = sangre,
                        onValueChange = { sangre = it },
                        readOnly = true,
                        label = { Text("Tipo de sangre") },
                        trailingIcon = {
                            IconButton(onClick = { expanded = true }) {
                                Icon(Icons.Filled.ArrowDropDown, contentDescription = "Dropdown")
                            }
                        }
                    )


                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = fechaUltimoExamen,
                        onValueChange = { fechaUltimoExamen = it },
                        label = { Text("Fecha del ultimo examen") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = enfermedades,
                        onValueChange = { enfermedades = it },
                        label = { Text("Enfermedades") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = cirugias,
                        onValueChange = { cirugias = it },
                        label = { Text("Cirugias") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = fechaCirugias,
                        onValueChange = { fechaCirugias = it },
                        label = { Text("Fecha de las cirugias") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = medicamentos,
                        onValueChange = { medicamentos = it },
                        label = { Text("Medicamentos") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = dosis,
                        onValueChange = { dosis = it },
                        label = { Text("Dosis") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = usoMedicamentos,
                        onValueChange = { usoMedicamentos = it },
                        label = { Text("Uso de medicamentos") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = alergias,
                        onValueChange = { alergias = it },
                        label = { Text("Alergias") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = enfermedadesCronicas,
                        onValueChange = { enfermedadesCronicas = it },
                        label = { Text("Enfermedades cronicas") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = antecedentesPersonales,
                        onValueChange = { antecedentesPersonales = it },
                        label = { Text("Antecedentes personales") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(

                        onClick = {
                            if (id.isBlank() || nombre.isBlank() || apellido.isBlank() || edad.isBlank() || peso.isBlank() || altura.isBlank() || telefono.isBlank() || correo.isBlank() || sangre.isBlank()
                                || fechaUltimoExamen.isBlank() || enfermedades.isBlank() || cirugias.isBlank() || fechaCirugias.isBlank() || medicamentos.isBlank() || dosis.isBlank() || usoMedicamentos.isBlank() || alergias.isBlank() || enfermedadesCronicas.isBlank() || antecedentesPersonales.isBlank()
                            ) {
                                showDialogEmptylane = true
                            } else {

                                val historial = historial(
                                    id = id,
                                    nombre = nombre,
                                    apellido = apellido,
                                    edad = edad.toIntOrNull() ?: 0,
                                    peso = peso.toIntOrNull() ?: 0,
                                    altura = altura.toDoubleOrNull() ?: 0.0,
                                    telefono = telefono.toIntOrNull() ?: 0,
                                    correo = correo,
                                    sangre = sangre,
                                    fechaUltimoExamen = fechaUltimoExamen,
                                    enfermedades = enfermedades,
                                    cirugias = cirugias,
                                    fechaCirugias = fechaCirugias,
                                    medicamentos = medicamentos,
                                    dosis = dosis,
                                    usoMedicamentos = usoMedicamentos,
                                    alergias = alergias,
                                    enfermedadesCronicas = enfermedadesCronicas,
                                    antecedentesPersonales = antecedentesPersonales
                                )
                                historialRepository.addHistorial(historial, onSuccess = {
                                    onHistorialAdded()
                                }, onFailure = { })
                                onHistorialAdded()

                            }


                        }

                    ) {


                        Text(text = "Guardar Historial")
                    }

                    if (showDialogEmptylane) {
                        AlertDialog(
                            onDismissRequest = { showDialogEmptylane = false },
                            title = { Text("Error") },
                            text = { Text("Por favor llena todos los campos") },
                            confirmButton = {
                                TextButton(onClick = { showDialogEmptylane = false }) {
                                    Text("Ok")
                                }
                            }
                        )
                    }


                }
            }
        }

    }

}