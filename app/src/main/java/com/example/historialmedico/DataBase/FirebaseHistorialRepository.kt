// FirebaseHistorialRepository.kt
package com.example.historialmedico.DataBase

import com.google.firebase.firestore.FirebaseFirestore

class FirebaseHistorialRepository {

    private val db = FirebaseFirestore.getInstance()
    private val historialCollection = db.collection("historiales")

    // Método para obtener historiales
    fun getHistorial(onSuccess: (List<historial>) -> Unit, onFailure: (Exception) -> Unit) {
        historialCollection.get()
            .addOnSuccessListener { result ->
                val historialList = result.mapNotNull { document ->
                    document.toObject(historial::class.java).apply { id = document.id }
                }
                onSuccess(historialList)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    // Método para actualizar un historial
    fun updateHistorial(historial: historial, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        historial.id?.let {
            historialCollection.document(it)
                .set(historial)  // Esto reemplaza los datos completos del documento
                .addOnSuccessListener {
                    onSuccess()
                }
                .addOnFailureListener { exception ->
                    onFailure(exception)
                }
        }
    }

    // Método para agregar un historial (si es necesario)
    fun addHistorial(historial: historial, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        historialCollection.add(historial)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }
}
