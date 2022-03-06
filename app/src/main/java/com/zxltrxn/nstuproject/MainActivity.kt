package com.zxltrxn.nstuproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.zxltrxn.nstuproject.ui.theme.NSTUProjectTheme
import com.zxltrxn.nstuproject.navigation.BottomNavItem
import com.zxltrxn.nstuproject.navigation.BottomNavigationBar
import com.zxltrxn.nstuproject.navigation.Navigation
import com.zxltrxn.nstuproject.navigation.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NSTUProjectTheme {
                App()
            }
        }
    }
}

@Composable
fun App(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        name = stringResource(R.string.home_screen_name),
                        route = Screen.Home.route,
                        icon = Icons.Default.Home
                    ),
                    BottomNavItem(name = stringResource(R.string.contacts_screen_name),
                        route = Screen.Contacts.route,
                        icon = Icons.Default.Phone
                    )
                ),
                navController = navController,
                onItemClick = {navController.navigate(it.route)})
        }
    ){
        Navigation(navController = navController)
    }
}