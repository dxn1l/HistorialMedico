package com.example.historialmedico.Screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.historialmedico.DataBase.FirebaseHistorialRepository
import com.example.historialmedico.DataBase.historial

@Composable
fun EditHistorialScreen(historialId: String, onBackToView: () -> Unit) {
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
        EditHistorialScreenContent(historial = historial!!, repository = repository, onBackToView = onBackToView)
    } else {
        errorMessage?.let { Text(text = it, color = MaterialTheme.colorScheme.error) }
    }
}

@Composable
fun EditHistorialScreenContent(historial: historial, repository: FirebaseHistorialRepository, onBackToView: () -> Unit) {
    val nombre = remember { mutableStateOf(historial.nombre) }
    val DNI = remember { mutableStateOf(historial.DNI) }
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
    val alergias = remember { mutableStateOf(historial.alergias) }
    val enfermedadesCronicas = remember { mutableStateOf(historial.enfermedadesCronicas) }
    val antecedentesPersonales = remember { mutableStateOf(historial.antecedentesPersonales) }

    var nombreError by remember { mutableStateOf(false) }
    var dniError by remember { mutableStateOf(false) }
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
        !dniError && !nombreError && !apellidoError && !edadError && !pesoError && !alturaError && !telefonoError && !correoError && !fechaUltimoExamenError && !enfermedadesError && !cirugiasError && !fechaCirugiasError && !medicamentosError && !alergiasError && !enfermedadesCronicasError && !antecedentesPersonalesError

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
                        value = nombre.value,
                        onValueChange = {
                            nombre.value = it
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
                        value = apellido.value,
                        onValueChange = {
                            apellido.value = it
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
                        value = DNI.value,
                        onValueChange = {
                            if (it.matches(Regex("^\\d{0,8}[A-Za-z]?$"))) {
                                DNI.value = it
                                dniError = !it.matches(Regex("^\\d{8}[A-Za-z]$"))
                            } else {
                                dniError = true
                            }
                        },
                        label = { Text("DNI") },
                        isError = dniError
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
                        value = edad.value,
                        onValueChange = {
                            if (it.length <= 3) {
                                edad.value = it
                                edadError = !it.matches(Regex("^[0-9]*$"))
                            }
                        },
                        label = { Text("Edad") },
                        isError = edadError
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
                        value = peso.value,
                        onValueChange = {
                            if (it.matches(Regex("^\\d{0,3}(\\.\\d{0,3})?$"))) {
                                peso.value = it
                                pesoError = false
                            } else {
                                pesoError = true
                            }
                        },
                        label = { Text("Peso") },
                        isError = pesoError
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
                        value = altura.value,
                        onValueChange = {
                            if (it.matches(Regex("^\\d?(\\.\\d{0,3})?$"))) {
                                altura.value = it
                                alturaError = false
                            } else {
                                alturaError = true
                            }
                        },
                        label = { Text("Altura") },
                        isError = alturaError
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
                        value = telefono.value,
                        onValueChange = {
                            if (it.length <= 9) {
                                telefono.value = it
                                telefonoError = !it.matches(Regex("^[0-9]*$"))
                            }
                        },
                        label = { Text("Teléfono") },
                        isError = telefonoError
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
                        value = correo.value,
                        onValueChange = {
                            correo.value = it
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
                                    sangre.value = bloodType
                                    expanded = false
                                }
                            )
                        }
                    }
                    OutlinedTextField(
                        value = sangre.value,
                        onValueChange = { sangre.value = it },
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
                        value = fechaUltimoExamen.value,
                        onValueChange = {
                            fechaUltimoExamen.value = it
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
                        value = enfermedades.value,
                        onValueChange = {
                            enfermedades.value = it
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
                        value = cirugias.value,
                        onValueChange = {
                            cirugias.value = it
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
                        value = fechaCirugias.value,
                        onValueChange = {
                            fechaCirugias.value = it
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
                        value = medicamentos.value,
                        onValueChange = {
                            medicamentos.value = it
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
                        value = alergias.value,
                        onValueChange = {
                            alergias.value = it
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
                        value = enfermedadesCronicas.value,
                        onValueChange = {
                            enfermedadesCronicas.value = it
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
                        value = antecedentesPersonales.value,
                        onValueChange = {
                            antecedentesPersonales.value = it
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
                            val updatedHistorial = historial.copy(
                                nombre = nombre.value,
                                apellido = apellido.value,
                                DNI = DNI.value,
                                edad = edad.value.toIntOrNull() ?: 0,
                                peso = peso.value.toDoubleOrNull() ?: 0.0,
                                altura = altura.value.toDoubleOrNull() ?: 0.0,
                                telefono = telefono.value.toIntOrNull() ?: 0,
                                correo = correo.value,
                                sangre = sangre.value,
                                fechaUltimoExamen = fechaUltimoExamen.value,
                                enfermedades = enfermedades.value,
                                cirugias = cirugias.value,
                                fechaCirugias = fechaCirugias.value,
                                medicamentos = medicamentos.value,
                                alergias = alergias.value,
                                enfermedadesCronicas = enfermedadesCronicas.value,
                                antecedentesPersonales = antecedentesPersonales.value
                            )
                            repository.updateHistorial(updatedHistorial, onSuccess = {
                                onBackToView()
                            }, onFailure = {
                                // Handle failure
                            })
                        },
                        enabled = isFormValid
                    ) {
                        Text("Guardar cambios")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            onBackToView()
                        }
                    ) {
                        Text("Cancelar")
                    }
                }
            }
        }
    }
}