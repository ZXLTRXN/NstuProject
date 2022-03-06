package com.zxltrxn.nstuproject.features.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.zxltrxn.nstuproject.features.web_view.presentation.Page
import com.zxltrxn.nstuproject.navigation.Screen

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable{
                navController.navigate(Screen.WebView.withArgs(Page.PERSONAL_AREA.url))
            },
            text = "webView")
    }
}