package com.example.historialmedico.DataBase

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
        if (userId != null){
            historial.userId = userId
            historialCollection.add(historial)
                .addOnSuccessListener { documentReference ->
                    historial.id = documentReference.id
                    onSuccess()
                }
                    .addOnFailureListener { exception -> onFailure(exception) }
             }
        else {
            onFailure(Exception("User not authenticated"))
        }
    }

    fun getHistorial(onSuccess: (List<historial>) -> Unit, onFailure: (Exception) -> Unit) {
    val userId = getUserId()
    if (userId != null) {
        historialCollection.whereEqualTo("userId", userId).get()
            .addOnSuccessListener { result ->
                val historial = result.toObjects(historial::class.java)
                onSuccess(historial)
            }
            .addOnFailureListener { exception -> onFailure(exception) }
    } else {
        onFailure(Exception("User not authenticated"))
    }
}


}