package com.example.historialmedico.DataBase

import android.os.Parcel
import android.os.Parcelable

data class historial(
    var userId: String? = null,
    var id: String? = null,
    var nombre: String = "",
    var apellido: String = "",
    var edad: Int = 0,
    var peso: Int = 0,
    var altura: Double = 0.0,
    var telefono: Int = 0,
    var correo: String = "",
    var sangre: String = "",
    var fechaUltimoExamen: String = "",
    var enfermedades: String = "",
    var cirugias: String = "",
    var fechaCirugias: String = "",
    var medicamentos: String = "",
    var dosis: String = "",
    var usoMedicamentos: String = "",
    var alergias: String = "",
    var enfermedadesCronicas: String = "",
    var antecedentesPersonales: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        userId = parcel.readString(),
        id = parcel.readString(),
        nombre = parcel.readString() ?: "",
        apellido = parcel.readString() ?: "",
        edad = parcel.readInt(),
        peso = parcel.readInt(),
        altura = parcel.readDouble(),
        telefono = parcel.readInt(),
        correo = parcel.readString() ?: "",
        sangre = parcel.readString() ?: "",
        fechaUltimoExamen = parcel.readString() ?: "",
        enfermedades = parcel.readString() ?: "",
        cirugias = parcel.readString() ?: "",
        fechaCirugias = parcel.readString() ?: "",
        medicamentos = parcel.readString() ?: "",
        dosis = parcel.readString() ?: "",
        usoMedicamentos = parcel.readString() ?: "",
        alergias = parcel.readString() ?: "",
        enfermedadesCronicas = parcel.readString() ?: "",
        antecedentesPersonales = parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userId)
        parcel.writeString(id)
        parcel.writeString(nombre)
        parcel.writeString(apellido)
        parcel.writeInt(edad)
        parcel.writeInt(peso)
        parcel.writeDouble(altura)
        parcel.writeInt(telefono)
        parcel.writeString(correo)
        parcel.writeString(sangre)
        parcel.writeString(fechaUltimoExamen)
        parcel.writeString(enfermedades)
        parcel.writeString(cirugias)
        parcel.writeString(fechaCirugias)
        parcel.writeString(medicamentos)
        parcel.writeString(dosis)
        parcel.writeString(usoMedicamentos)
        parcel.writeString(alergias)
        parcel.writeString(enfermedadesCronicas)
        parcel.writeString(antecedentesPersonales)
    }

    override fun describeContents(): Int {
        return 0
    }

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

        @JvmField
        val CREATOR = object : Parcelable.Creator<historial> {
            override fun createFromParcel(parcel: Parcel): historial {
                return historial(parcel)
            }

            override fun newArray(size: Int): Array<historial?> {
                return arrayOfNulls(size)
            }
        }
    }
}
