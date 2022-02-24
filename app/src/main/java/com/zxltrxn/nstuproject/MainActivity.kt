package com.zxltrxn.nstuproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.zxltrxn.nstuproject.ui.theme.NSTUProjectTheme
import com.zxltrxn.nstuproject.web_view_feature.presentation.Pages
import com.zxltrxn.nstuproject.web_view_feature.presentation.WebViewScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NSTUProjectTheme {
                WebViewScreen(Pages.SEARCH_BACHELORS_PROGRAMS)
            }
        }
    }
}