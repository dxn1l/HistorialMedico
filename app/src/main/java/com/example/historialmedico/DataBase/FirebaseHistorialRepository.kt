package com.example.historialmedico.DataBase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseHistorialRepository {

    private val db = FirebaseFirestore.getInstance()
    private val historialCollection = db.collection("historial")
    private val auth = FirebaseAuth.getInstance()

    private fun getUserId(): String? {
        return auth.currentUser?.uid
    }

    fun addHistorial(historial: historial, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val userId = getUserId()
        if (userId != null) {
            historial.userId = userId
            // First, delete any existing historial for the user
            historialCollection
                .whereEqualTo("userId", userId)
                .get()
                .addOnSuccessListener { result ->
                    for (document in result.documents) {
                        historialCollection.document(document.id).delete()
                    }
                    // After deleting, add the new historial
                    historialCollection.add(historial)
                        .addOnSuccessListener { documentReference ->
                            historial.id = documentReference.id
                            onSuccess()
                        }
                        .addOnFailureListener { exception -> onFailure(exception) }
                }
                .addOnFailureListener { exception -> onFailure(exception) }
        } else {
            onFailure(Exception("User not authenticated"))
        }
    }

    fun getHistorial(onSuccess: (List<historial>) -> Unit, onFailure: (Exception) -> Unit) {
        val userId = getUserId()
        if (userId != null) {
            historialCollection
                .whereEqualTo("userId", userId)  // Filtrar solo por el usuario actual
                .get()
                .addOnSuccessListener { result ->
                    val historialList = result.documents.mapNotNull { document ->
                        document.toObject(historial::class.java)
                    }

                    Log.d("FirebaseHistorialRepository", "Historial recuperado: $historialList")
                    onSuccess(historialList)
                }
                .addOnFailureListener { exception ->
                    Log.e("FirebaseHistorialRepository", "Error al recuperar historial", exception)
                    onFailure(exception)
                }
        } else {
            onFailure(Exception("User not authenticated"))
        }
    }


    fun deleteHistorial(
        historialId: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val userId = getUserId()
        if (userId != null) {
            historialCollection
                .whereEqualTo("userId", userId)
                .whereEqualTo("id", historialId)  // Buscar por el ID del historial
                .get()
                .addOnSuccessListener { result ->
                    if (!result.isEmpty) {
                        // Eliminar el documento
                        val documentId = result.documents.first().id
                        historialCollection.document(documentId).delete()
                            .addOnSuccessListener {
                                Log.d(
                                    "FirebaseHistorialRepository",
                                    "Historial eliminado exitosamente"
                                )
                                onSuccess()
                            }
                            .addOnFailureListener { exception ->
                                Log.e(
                                    "FirebaseHistorialRepository",
                                    "Error al eliminar historial",
                                    exception
                                )
                                onFailure(exception)
                            }
                    } else {
                        onFailure(Exception("Historial no encontrado para eliminar"))
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e(
                        "FirebaseHistorialRepository",
                        "Error al buscar historial para eliminar",
                        exception
                    )
                    onFailure(exception)
                }
        } else {
            onFailure(Exception("User not authenticated"))
        }
    }
}

