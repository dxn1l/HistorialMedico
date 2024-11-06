package com.example.historialmedico.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {

  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  var isLogin by remember { mutableStateOf(true) }
  var errorMessage by remember { mutableStateOf<String?>(null) }
  val auth = FirebaseAuth.getInstance()


  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Text(text = if (isLogin) "Login" else "Register", style = MaterialTheme.typography.headlineMedium)
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedTextField(
      value = email,
      onValueChange = { email = it },
      label = { Text("Email") },
      keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
    )
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
      value = password,
      onValueChange = { password = it },
      label = { Text("Password") },
      visualTransformation = PasswordVisualTransformation(),
      keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(onClick = {
      if (isLogin) {
        auth.signInWithEmailAndPassword(email, password)
          .addOnCompleteListener { task ->
            if (task.isSuccessful) {
              onLoginSuccess()
            } else {
              errorMessage = task.exception?.message
            }
          }
      } else {
        auth.createUserWithEmailAndPassword(email, password)
          .addOnCompleteListener { task ->
            if (task.isSuccessful) {
              onLoginSuccess()
            } else {
              errorMessage = task.exception?.message
            }
          }
      }
    }) {
      Text(text = if (isLogin) "Login" else "Register")
    }
    Spacer(modifier = Modifier.height(8.dp))
    TextButton(onClick = { isLogin = !isLogin }) {
      Text(text = if (isLogin) "Don't have an account? Register" else "Already have an account? Login")
    }
    errorMessage?.let {
      Spacer(modifier = Modifier.height(8.dp))
      Text(text = it, color = MaterialTheme.colorScheme.error)
    }
  }


}