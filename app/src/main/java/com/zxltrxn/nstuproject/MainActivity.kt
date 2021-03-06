package com.zxltrxn.nstuproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.zxltrxn.nstuproject.ui.theme.NSTUProjectTheme
import com.zxltrxn.nstuproject.navigation.BottomNavigationBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                ) { contentPadding ->
                    DestinationsNavHost(
                        modifier = Modifier.padding(contentPadding),
                        navGraph = NavGraphs.root,
                        navController = navController
                    )
                }
            }
        }
    }
}
