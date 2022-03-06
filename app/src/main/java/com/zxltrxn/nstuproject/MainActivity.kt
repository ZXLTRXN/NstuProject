package com.zxltrxn.nstuproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.zxltrxn.nstuproject.features.NavGraphs
import com.zxltrxn.nstuproject.ui.theme.NSTUProjectTheme
import com.zxltrxn.nstuproject.navigation.BottomNavigationBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NSTUProjectTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navController = navController)
                    }
                ){
                    DestinationsNavHost(navGraph = NavGraphs.root, navController = navController)
                }
            }
        }
    }
}
