package com.example.historialmedico.DataBase

import android.os.Parcel
import android.os.Parcelable

data class historial(
    var userId: String? = null,
    var id: String? = null,
    var nombre: String = "",
    var apellido: String = "",
    var DNI: String = "",
    var edad: Int = 0,
    var peso: Double = 0.0,
    var altura: Double = 0.0,
    var telefono: Int = 0,
    var correo: String = "",
    var sangre: String = "",
    var fechaUltimoExamen: String = "",
    var enfermedades: String = "",
    var cirugias: String = "",
    var fechaCirugias: String = "",
    var medicamentos: String = "",
    var alergias: String = "",
    var enfermedadesCronicas: String = "",
    var antecedentesPersonales: String = ""
)

