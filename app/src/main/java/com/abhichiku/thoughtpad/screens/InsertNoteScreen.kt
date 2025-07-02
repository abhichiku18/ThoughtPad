package com.abhichiku.thoughtpad.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.database.FirebaseDatabase
import java.util.*

@Composable
fun InsertNoteScreen(navController: NavHostController) {
    // State variables for text fields
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    val db = FirebaseFirestore.getInstance()
    val notesDBRef = db.collection("notes")
    val realtimeDbRef = FirebaseDatabase.getInstance().getReference("notifications")

    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (title.isNotBlank() && content.isNotBlank()) {
                    val newNote = hashMapOf(
                        "id" to UUID.randomUUID().toString(),
                        "title" to title,
                        "content" to content
                    )
                    notesDBRef.add(newNote)
                        .addOnSuccessListener {
                            navController.navigate("notes") // go back to notes screen
                            realtimeDbRef.child("message").setValue("New note added: $title")
                            navController.navigate("notes")
                        }
                        .addOnFailureListener {
                            Log.e("InsertNote", "Failed to add note", it)
                            Toast.makeText(context, "Failed to add note", Toast.LENGTH_SHORT).show()

                        }
                }
            }) {
                Icon(Icons.Filled.Done, contentDescription = "Add Note")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(15.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Insert Note Data",
                style = TextStyle(fontSize = 30.sp),
                fontWeight = FontWeight.Bold
            )

            // Title TextField
            TextField(
                value = title,
                onValueChange = { title = it },
                label = {
                    Text(
                        text = "Enter Title of Your Notes",
                        style = TextStyle(fontSize = 15.sp, color = Color.Black)
                    )
                },
                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )

            // Content TextField
            TextField(
                value = content,
                onValueChange = { content = it },
                label = {
                    Text(
                        text = "Enter Content",
                        style = TextStyle(fontSize = 15.sp, color = Color.Black)
                    )
                },
                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .heightIn(min = 100.dp, max = 300.dp),
                singleLine = false
            )
        }
    }
}


