package com.abhichiku.thoughtpad.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.abhichiku.thoughtpad.models.Notes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(navController: NavHostController, modifier: Modifier = Modifier) {

    //Firebase Realtime Database and Cloud Firestore Database

    val db = FirebaseFirestore.getInstance()
    val notesDBref = db.collection("notes")
    val notesList = remember { mutableStateListOf<Notes>() }
    val dataValue = remember { mutableStateOf(false) }
    val snackbarHostState = remember { androidx.compose.material3.SnackbarHostState() }

    // Load data from Firestore
    LaunchedEffect(Unit) {
        notesDBref.addSnapshotListener { values, error ->
            if (error == null) {
                val data = values?.toObjects(Notes::class.java)
                notesList.clear()
                if (data != null) {
                    notesList.addAll(data)
                }
                dataValue.value = true
            } else {
                dataValue.value = false
            }
        }
        //  Realtime DB listener
        val realtimeDbRef = com.google.firebase.database.FirebaseDatabase.getInstance()
            .getReference("notifications")

        realtimeDbRef.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(snapshot: com.google.firebase.database.DataSnapshot) {
                val message = snapshot.child("message").getValue(String::class.java)
                if (!message.isNullOrEmpty()) {
                    kotlinx.coroutines.GlobalScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }

            override fun onCancelled(error: com.google.firebase.database.DatabaseError) {
                println("Realtime DB error: ${error.message}")
            }
        })
    }
    // Scaffold gives us a top bar and floating action button
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("NOTES", style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold)) },
                actions = {
                    IconButton(onClick = {
                        // Logout logic
                        FirebaseAuth.getInstance().signOut()
                        navController.navigate("login") {
                            popUpTo("notes") { inclusive = true }
                        }
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Logout")

                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("insertnotes") // navigate to InsertNoteScreen
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Note")
            }
        }
    ) { innerPadding ->
        Box(modifier = modifier.padding(innerPadding)) {
            if (dataValue.value) {
                LazyColumn(modifier = Modifier.padding(16.dp)) {
                    items(notesList) { note ->
                        NoteListItem(note, notesDBref)
                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(25.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}
