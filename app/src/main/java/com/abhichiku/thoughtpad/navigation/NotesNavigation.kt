package com.abhichiku.thoughtpad.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.abhichiku.thoughtpad.screens.InsertNoteScreen
import com.abhichiku.thoughtpad.screens.LoginScreen
import com.abhichiku.thoughtpad.screens.NotesScreen
import com.abhichiku.thoughtpad.screens.SignupScreen
import com.abhichiku.thoughtpad.screens.SplashScreen

//Used For Navigation
@Composable
fun NotesNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "splash" // first screen
    ) {
        composable("splash") {
            SplashScreen(navController = navController)
        }
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("signup") {
            SignupScreen(navController = navController)
        }
        composable("insertnotes") {
            InsertNoteScreen(navController = navController)
        }
        composable("notes") {
            NotesScreen(navController = navController) // pass navController
        }
    }
}
