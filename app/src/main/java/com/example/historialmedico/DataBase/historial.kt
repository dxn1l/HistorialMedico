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

    companion object CREATOR : Parcelable.Creator<historial> {
        override fun createFromParcel(parcel: Parcel): historial {
            return historial(parcel)
        }

        override fun newArray(size: Int): Array<historial?> {
            return arrayOfNulls(size)
        }
    }
}