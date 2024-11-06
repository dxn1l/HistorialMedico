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
import androidx.compose.material3.MaterialTheme

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
    var idError by remember { mutableStateOf(false) }
    var nombreError by remember { mutableStateOf(false) }
    var apellidoError by remember { mutableStateOf(false) }
    var edadError by remember { mutableStateOf(false) }
    var pesoError by remember { mutableStateOf(false) }
    var alturaError by remember { mutableStateOf(false) }
    var telefonoError by remember { mutableStateOf(false) }
    var fechaUltimoExamenError by remember { mutableStateOf(false) }
    var enfermedadesError by remember { mutableStateOf(false) }
    var cirugiasError by remember { mutableStateOf(false) }
    var fechaCirugiasError by remember { mutableStateOf(false) }
    var medicamentosError by remember { mutableStateOf(false) }
    var dosisError by remember { mutableStateOf(false) }
    var alergiasError by remember { mutableStateOf(false) }
    var enfermedadesCronicasError by remember { mutableStateOf(false) }
    var antecedentesPersonalesError by remember { mutableStateOf(false) }
    var showErrorMessage by remember { mutableStateOf(false) }
    val bloodTypes = listOf("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")
    var expanded by remember { mutableStateOf(false) }

    val isFormValid = !idError && !nombreError && !apellidoError && !edadError && !pesoError && !alturaError && !telefonoError && !fechaUltimoExamenError && !enfermedadesError && !cirugiasError && !fechaCirugiasError && !medicamentosError && !dosisError && !alergiasError && !enfermedadesCronicasError && !antecedentesPersonalesError

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
                        onValueChange = {
                            id = it
                            idError = it.isBlank()
                        },
                        label = { Text("ID") },
                        isError = idError
                    )
                    if (idError) {
                        Text(
                            text = "ID inválido, escriba correctamente",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = nombre,
                        onValueChange = {
                            nombre = it
                            nombreError = !it.matches(Regex("^[a-zA-Z\\s]*$"))
                        },
                        label = { Text("Nombre") },
                        isError = nombreError
                    )
                    if (nombreError) {
                        Text(
                            text = "Nombre inválido, escriba su nombre correctamente",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = apellido,
                        onValueChange = {
                            apellido = it
                            apellidoError = !it.matches(Regex("^[a-zA-Z\\s]*$"))
                        },
                        label = { Text("Apellido") },
                        isError = apellidoError
                    )
                    if (apellidoError) {
                        Text(
                            text = "Apellido inválido, escriba su apellido correctamente",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = edad,
                        onValueChange = {
                            edad = it
                            edadError = !it.matches(Regex("^[0-9]*$"))
                        },
                        label = { Text("Edad") },
                        isError = edadError
                    )
                    if (edadError) {
                        Text(
                            text = "Edad inválida, escriba su edad correctamente",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = peso,
                        onValueChange = {
                            peso = it
                            pesoError = false
                        },
                        label = { Text("Peso") },
                        isError = pesoError
                    )
                    if (pesoError) {
                        Text(
                            text = "Peso inválido, escriba su peso correctamente",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = altura,
                        onValueChange = {
                            altura = it
                            alturaError = false
                        },
                        label = { Text("Altura") },
                        isError = alturaError
                    )
                    if (alturaError) {
                        Text(
                            text = "Altura inválida, escriba su altura correctamente",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = telefono,
                        onValueChange = {
                            telefono = it
                            telefonoError = !it.matches(Regex("^[0-9]*$"))
                        },
                        label = { Text("Teléfono") },
                        isError = telefonoError
                    )
                    if (telefonoError) {
                        Text(
                            text = "Teléfono inválido, escriba su teléfono correctamente",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

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
                                }
                            )
                        }
                    }
                    OutlinedTextField(
                        value = sangre,
                        onValueChange = { sangre = it },
                        readOnly = true,
                        label = { Text("Tipo de sangre") },
                        trailingIcon = {
                            IconButton(onClick = { expanded = true }) {
                                Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = fechaUltimoExamen,
                        onValueChange = {
                            fechaUltimoExamen = it
                            fechaUltimoExamenError = false
                        },
                        label = { Text("Fecha del último examen") },
                        isError = fechaUltimoExamenError
                    )
                    if (fechaUltimoExamenError) {
                        Text(
                            text = "Fecha del último examen inválida, escriba correctamente",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = enfermedades,
                        onValueChange = {
                            enfermedades = it
                            enfermedadesError = !it.matches(Regex("^[a-zA-Z\\s]*$"))
                        },
                        label = { Text("Enfermedades") },
                        isError = enfermedadesError
                    )
                    if (enfermedadesError) {
                        Text(
                            text = "Enfermedades inválidas, escriba correctamente",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = cirugias,
                        onValueChange = {
                            cirugias = it
                            cirugiasError = !it.matches(Regex("^[a-zA-Z\\s]*$"))
                        },
                        label = { Text("Cirugias") },
                        isError = cirugiasError
                    )
                    if (cirugiasError) {
                        Text(
                            text = "Cirugias inválidas, escriba correctamente",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = fechaCirugias,
                        onValueChange = {
                            fechaCirugias = it
                            fechaCirugiasError = false
                        },
                        label = { Text("Fecha de las cirugias") },
                        isError = fechaCirugiasError
                    )
                    if (fechaCirugiasError) {
                        Text(
                            text = "Fecha de las cirugias inválida, escriba correctamente",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = medicamentos,
                        onValueChange = {
                            medicamentos = it
                            medicamentosError = !it.matches(Regex("^[a-zA-Z\\s]*$"))
                        },
                        label = { Text("Medicamentos") },
                        isError = medicamentosError
                    )
                    if (medicamentosError) {
                        Text(
                            text = "Medicamentos inválidos, escriba correctamente",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = dosis,
                        onValueChange = {
                            dosis = it
                            dosisError = false
                        },
                        label = { Text("Dosis") },
                        isError = dosisError
                    )
                    if (dosisError) {
                        Text(
                            text = "Dosis inválida, escriba correctamente",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = usoMedicamentos,
                        onValueChange = { usoMedicamentos = it },
                        label = { Text("Uso de medicamentos") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = alergias,
                        onValueChange = {
                            alergias = it
                            alergiasError = !it.matches(Regex("^[a-zA-Z\\s]*$"))
                        },
                        label = { Text("Alergias") },
                        isError = alergiasError
                    )
                    if (alergiasError) {
                        Text(
                            text = "Alergias inválidas, escriba correctamente",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = enfermedadesCronicas,
                        onValueChange = {
                            enfermedadesCronicas = it
                            enfermedadesCronicasError = !it.matches(Regex("^[a-zA-Z\\s]*$"))
                        },
                        label = { Text("Enfermedades crónicas") },
                        isError = enfermedadesCronicasError
                    )
                    if (enfermedadesCronicasError) {
                        Text(
                            text = "Enfermedades crónicas inválidas, escriba correctamente",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = antecedentesPersonales,
                        onValueChange = {
                            antecedentesPersonales = it
                            antecedentesPersonalesError = !it.matches(Regex("^[a-zA-Z\\s]*$"))
                        },
                        label = { Text("Antecedentes personales") },
                        isError = antecedentesPersonalesError
                    )
                    if (antecedentesPersonalesError) {
                        Text(
                            text = "Antecedentes personales inválidos, escriba correctamente",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    if (showErrorMessage) {
                        Text(
                            text = "No cumples todos los requisitos",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            if (id.isBlank() || nombre.isBlank() || apellido.isBlank() || edad.isBlank() || peso.isBlank() || altura.isBlank() || telefono.isBlank() || correo.isBlank() || sangre.isBlank() || fechaUltimoExamen.isBlank() || enfermedades.isBlank() || cirugias.isBlank() || fechaCirugias.isBlank() || medicamentos.isBlank() || dosis.isBlank() || usoMedicamentos.isBlank() || alergias.isBlank() || enfermedadesCronicas.isBlank() || antecedentesPersonales.isBlank()) {
                                showDialogEmptylane = true
                            } else {
                                historialRepository.addHistorial(
                                    historial(
                                        id = id,
                                        nombre = nombre,
                                        apellido = apellido,
                                        edad = edad.toInt(),
                                        peso = peso.toInt(),
                                        altura = altura.toDouble(),
                                        telefono = telefono.toInt(),
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
                                    ),
                                    onSuccess = { onHistorialAdded() },
                                    onFailure = { showDialogEmptylane = true }
                                )
                            }
                        },
                        enabled = isFormValid
                    ) {
                        Text(text = "Guardar Historial")
                    }

                    if (showDialogEmptylane) {
                        AlertDialog(
                            onDismissRequest = { showDialogEmptylane = false },
                            title = { Text("Error") },
                            text = { Text("Por favor completa todos los campos correctamente") },
                            confirmButton = {
                                TextButton(onClick = { showDialogEmptylane = false }) {
                                    Text("OK")
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}