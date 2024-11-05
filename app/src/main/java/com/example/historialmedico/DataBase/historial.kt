package com.example.historialmedico.DataBase

data class historial(

    var id: String? = null ,
    var nombre: String = "",
    var apellido: String = "",
    var edad: Int = 0,
    var peso: Int  = 0,
    var altura: Double = 0.0,
    var telefono : Int = 0,
    var correo: String = "",
    var sangre : String = "",
    var fechaUltimoExamen: String = "",
    var enfermedades : String = "",
    var cirugias : String = "",
    var fechaCirugias : String = "",
    var medicamentos : String = "",
    var dosis : String = "",
    var usoMedicamentos : String = "",
    var alergias : String = "",
    var enfermedadesCronicas : String = "",
    var antecedentesPersonales : String = "",

)

