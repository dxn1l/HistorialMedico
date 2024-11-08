package com.example.historialmedico.Screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction

@Composable
fun AddHistorialScreen(
    historialRepository: FirebaseHistorialRepository,
    onHistorialAdded: () -> Unit,
    onBacktoMenu: () -> Unit
) {
    var id by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var DNI by remember { mutableStateOf("") }
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
    var alergias by remember { mutableStateOf("") }
    var enfermedadesCronicas by remember { mutableStateOf("") }
    var antecedentesPersonales by remember { mutableStateOf("") }

    var showDialogEmptylane by remember { mutableStateOf(false) }
    var idError by remember { mutableStateOf(false) }
    var dniError by remember { mutableStateOf(false) }
    var nombreError by remember { mutableStateOf(false) }
    var apellidoError by remember { mutableStateOf(false) }
    var edadError by remember { mutableStateOf(false) }
    var pesoError by remember { mutableStateOf(false) }
    var alturaError by remember { mutableStateOf(false) }
    var telefonoError by remember { mutableStateOf(false) }
    var correoError by remember { mutableStateOf(false) }
    var fechaUltimoExamenError by remember { mutableStateOf(false) }
    var enfermedadesError by remember { mutableStateOf(false) }
    var cirugiasError by remember { mutableStateOf(false) }
    var fechaCirugiasError by remember { mutableStateOf(false) }
    var medicamentosError by remember { mutableStateOf(false) }
    var alergiasError by remember { mutableStateOf(false) }
    var enfermedadesCronicasError by remember { mutableStateOf(false) }
    var antecedentesPersonalesError by remember { mutableStateOf(false) }
    var showErrorMessage by remember { mutableStateOf(false) }
    val bloodTypes = listOf("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")
    var expanded by remember { mutableStateOf(false) }

    val isFormValid =
        !idError && !dniError && !nombreError && !apellidoError && !edadError && !pesoError && !alturaError && !telefonoError && !correoError && !fechaUltimoExamenError && !enfermedadesError && !cirugiasError && !fechaCirugiasError && !medicamentosError && !alergiasError && !enfermedadesCronicasError && !antecedentesPersonalesError

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
                            text = "No se permiten Números",
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
                            text = "No se permiten Números",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = DNI,
                        onValueChange = {
                            if (it.matches(Regex("^\\d{0,8}[A-Za-z]?$"))) {
                                DNI = it
                                dniError = !it.matches(Regex("^\\d{8}[A-Za-z]$"))
                            } else {
                                dniError = true
                            }
                        },
                        label = { Text("DNI") },
                        isError = dniError,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                    )
                    if (dniError) {
                        Text(
                            text = "DNI inválido, debe tener 8 dígitos seguidos de una letra",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = edad,
                        onValueChange = {
                            if (it.length <= 3) {
                                edad = it
                                edadError = !it.matches(Regex("^[0-9]*$"))
                            }
                        },
                        label = { Text("Edad") },
                        isError = edadError,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                    )
                    if (edadError) {
                        Text(
                            text = "No se permiten letras, ni más de tres dígitos",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = peso,
                        onValueChange = {
                            if (it.matches(Regex("^\\d{0,3}(\\.\\d{0,3})?$"))) {
                                peso = it
                                pesoError = false
                            } else {
                                pesoError = true
                            }
                        },
                        label = { Text("Peso") },
                        isError = pesoError,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                    )
                    if (pesoError) {
                        Text(
                            text = "No se permiten letras, ni más de tres decimales ni más de tres dígitos en la parte entera",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = altura,
                        onValueChange = {
                            if (it.matches(Regex("^\\d?(\\.\\d{0,3})?$"))) {
                                altura = it
                                alturaError = false
                            } else {
                                alturaError = true
                            }
                        },
                        label = { Text("Altura") },
                        isError = alturaError,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                    )
                    if (alturaError) {
                        Text(
                            text = "No se permiten letras, ni más de tres decimales ni más de un digito en la parte entera",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = telefono,
                        onValueChange = {
                            if (it.length <= 9) {
                                telefono = it
                                telefonoError = !it.matches(Regex("^[0-9]*$"))
                            }
                        },
                        label = { Text("Teléfono") },
                        isError = telefonoError,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                    )
                    if (telefonoError) {
                        Text(
                            text = "no se permiten letras , ni más de 9 dígitos",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = correo,
                        onValueChange = {
                            correo = it
                            correoError =
                                !it.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+.[A-Za-z0-9.-]$"))
                        },
                        label = { Text("Correo") },
                        isError = correoError
                    )
                    if (correoError) {
                        Text(
                            text = "El correo debe tener el formato: example@example.com",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

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
                            fechaUltimoExamenError = !it.matches(Regex("^\\d{4}-\\d{2}-\\d{2}$"))
                        },
                        label = { Text("Fecha del último examen") },
                        isError = fechaUltimoExamenError
                    )
                    if (fechaUltimoExamenError) {
                        Text(
                            text = "la fecha debe tener el  formato: YYYY-MM-DD",
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
                            text = "No se permiten números",
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
                            text = "No se permiten números",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = fechaCirugias,
                        onValueChange = {
                            fechaCirugias = it
                            fechaCirugiasError = !it.matches(Regex("^\\d{4}-\\d{2}-\\d{2}$"))
                        },
                        label = { Text("Fecha de las cirugias") },
                        isError = fechaCirugiasError
                    )
                    if (fechaCirugiasError) {
                        Text(
                            text = "La fecha debe tener el formato: YYYY-MM-DD",
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
                            text = "No se permiten números",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

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
                            text = "No se permiten números",
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
                            text = "No se permiten números",
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
                            text = "No se permiten números",
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
                            if (id.isBlank() || DNI.isBlank() || nombre.isBlank() || apellido.isBlank() || edad.isBlank() || peso.isBlank() || altura.isBlank() || telefono.isBlank() || correo.isBlank() || sangre.isBlank() || fechaUltimoExamen.isBlank() || enfermedades.isBlank() || cirugias.isBlank() || fechaCirugias.isBlank() || medicamentos.isBlank() || alergias.isBlank() || enfermedadesCronicas.isBlank() || antecedentesPersonales.isBlank()) {
                                showDialogEmptylane = true
                            } else {
                                historialRepository.addHistorial(
                                    historial(
                                        id = id,
                                        nombre = nombre,
                                        apellido = apellido,
                                        DNI = DNI,
                                        edad = edad.toInt(),
                                        peso = peso.toDouble(),
                                        altura = altura.toDouble(),
                                        telefono = telefono.toInt(),
                                        correo = correo,
                                        sangre = sangre,
                                        fechaUltimoExamen = fechaUltimoExamen,
                                        enfermedades = enfermedades,
                                        cirugias = cirugias,
                                        fechaCirugias = fechaCirugias,
                                        medicamentos = medicamentos,
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

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = { onBacktoMenu() },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = "Volver al menú")

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
}