package com.abhichiku.thoughtpad.models

    data class Notes(
        val id: String = "",        // Unique id (e.g., from Firestore)
        val title: String = "",
        val content: String = "",   // Note content

    )


