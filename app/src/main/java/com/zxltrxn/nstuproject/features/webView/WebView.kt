package com.zxltrxn.nstuproject.features.webView

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import com.ramcosta.composedestinations.annotation.Destination
import com.zxltrxn.nstuproject.commonComposable.LoadingIndicator
import com.zxltrxn.nstuproject.features.Page

@SuppressLint("SetJavaScriptEnabled")
@Destination
@Composable
fun WebViewScreen(
    url: String,
    cacheMode: Int = WebSettings.LOAD_CACHE_ELSE_NETWORK
) {
    var webView: WebView? = null
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()

    val backEnabled = remember { mutableStateOf(false) }
    val isLoading = remember { mutableStateOf(true) }

    if (isLoading.value) {
        LoadingIndicator()
    }

    AndroidView(
        modifier = Modifier.zIndex(1F),
        factory = {
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = CustomWebViewClient(isLoading, backEnabled)
                settings.cacheMode = cacheMode
                settings.javaScriptEnabled = true
//                settings.useWideViewPort = true
                webView = this

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && isDarkTheme) {
                    settings.forceDark = WebSettings.FORCE_DARK_ON
                }
                loadUrl(url)
            }
        }, update = {
            webView = it
        })

    BackHandler(enabled = backEnabled.value) {
        webView?.goBack()
    }
}

@Destination
@Composable
fun ContactsWebView() = WebViewScreen(Page.PHONE.url)

@Destination
@Composable
fun PersonalAreaWebView() = WebViewScreen(Page.PERSONAL_AREA.url)

fun View.smoothShow() {
    this.apply {
        alpha = 0f
        visibility = View.VISIBLE

        animate()
            .alpha(1f)
            .setDuration(300)
            .setListener(null)
    }
}