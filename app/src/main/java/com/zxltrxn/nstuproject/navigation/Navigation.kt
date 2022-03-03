package com.zxltrxn.nstuproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.zxltrxn.nstuproject.features.contacts.ContactsScreen
import com.zxltrxn.nstuproject.features.web_view.presentation.WebViewScreen


@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Contacts.route) {
            ContactsScreen()
        }
        composable(
            route = Screen.WebView.route + "/{url}",
            arguments = listOf(
                navArgument(name = "url"){
                    type = NavType.StringType
                }
            ))
        { entry->
            WebViewScreen(url = entry.arguments?.getString("url") ?: "https://nstu.ru")
        }
    }
}


