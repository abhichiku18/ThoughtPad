package com.abhichiku.thoughtpad.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authMessage = MutableStateFlow("")
    val authMessage: StateFlow<String> = _authMessage

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { onSuccess() }
                .addOnFailureListener { e ->
                    _authMessage.value = "Login failed: ${e.message ?: "Unknown error"}"
                }
        }
    }

    fun signup(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { onSuccess() }
                .addOnFailureListener { e ->
                    _authMessage.value = "Signup failed: ${e.message ?: "Unknown error"}"
                }
        }
    }
}