package com.abhichiku.thoughtpad.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.abhichiku.thoughtpad.models.Notes
import com.google.firebase.firestore.CollectionReference


//It is Structure for notes

@Composable
fun NoteListItem(
    note: Notes,
    notesDBref: CollectionReference
) {
    // State to control dropdown menu visibility
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
            .background(color = Color(0xFF50E3C2))
    ) {
        // update & delete actions
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(x = (-40).dp, y = 0.dp),
            properties = PopupProperties(clippingEnabled = true),
            modifier = Modifier.background(Color.White)
        ) {
            DropdownMenuItem(
                text = { Text("update", style = TextStyle(color = Color.Gray)) },
                onClick = {
                    expanded = false

                }
            )
            DropdownMenuItem(
                text = { Text("delete", style = TextStyle(color = Color.Gray)) },
                onClick = {
                    expanded = false
                    // Delete the note from Firestore by matching 'id'
                    notesDBref.whereEqualTo("id", note.id).get()
                        .addOnSuccessListener { querySnapshot ->
                            for (document in querySnapshot) {
                                notesDBref.document(document.id).delete()
                            }
                        }
                }
            )
        }

        // Icon to open the dropdown menu
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "Options",
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable { expanded = true }
        )

        //  title and description
        Column(modifier = Modifier.padding(15.dp)) {
            Text(
                text = note.title,
                style = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
            Text(
                text = note.content,
                style = TextStyle(
                    fontSize = 10.sp,
                    color = Color.Black
                )
            )
        }
    }
}
