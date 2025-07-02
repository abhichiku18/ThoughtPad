package com.abhichiku.thoughtpad.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.abhichiku.thoughtpad.viewmodel.AuthViewModel


//Sign Up Page
@Composable
fun SignupScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val message by authViewModel.authMessage.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sign Up", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )

        Button(
            onClick = {
                authViewModel.signup(email, password) {
                    navController.navigate("login") {
                        popUpTo("signup") { inclusive = true }
                    }
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Sign Up")
        }

        TextButton(
            onClick = { navController.navigate("login") },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Already have an account? Login")
        }

        if (message.isNotEmpty()) {
            Text(message, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 12.dp))
        }
    }
}