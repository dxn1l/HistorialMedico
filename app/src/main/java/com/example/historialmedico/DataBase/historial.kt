package com.example.historialmedico.DataBase

data class historial(


    var userId: String? = null,
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

) {
    override fun toString(): String {
        return "$nombre $apellido $edad $peso $altura $telefono $correo $sangre $fechaUltimoExamen $enfermedades $cirugias $fechaCirugias $medicamentos $dosis $usoMedicamentos $alergias $enfermedadesCronicas $antecedentesPersonales"
    }

    companion object {
        fun fromString(data: String): historial {
            val parts = data.split(" ")
            return historial(
                nombre = parts[0],
                apellido = parts[1],
                edad = parts[2].toInt(),
                peso = parts[3].toInt(),
                altura = parts[4].toDouble(),
                telefono = parts[5].toInt(),
                correo = parts[6],
                sangre = parts[7],
                fechaUltimoExamen = parts[8],
                enfermedades = parts[9],
                cirugias = parts[10],
                fechaCirugias = parts[11],
                medicamentos = parts[12],
                dosis = parts[13],
                usoMedicamentos = parts[14],
                alergias = parts[15],
                enfermedadesCronicas = parts[16],
                antecedentesPersonales = parts[17]
            )
        }
    }
}

