package com.zxltrxn.nstuproject.features.webView

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import com.ramcosta.composedestinations.annotation.Destination
import com.zxltrxn.nstuproject.R
import com.zxltrxn.nstuproject.commonComposable.ErrorMessage
import com.zxltrxn.nstuproject.commonComposable.LoadingIndicator
import com.zxltrxn.nstuproject.features.Page
import com.zxltrxn.nstuproject.features.parsing.commonDomain.ErrorCode
import com.zxltrxn.nstuproject.ui.spacing


@SuppressLint("SetJavaScriptEnabled")
@Destination
@Composable
fun WebViewScreen(
    page: Page
) {
    var webView: WebView? = null
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()

    val loadingPercent = remember { mutableStateOf(0) }
    val currentUrl = remember { mutableStateOf(page.url) }
    val isLoading = remember { mutableStateOf(true) }
    val errorCode = remember { mutableStateOf<Int?>(null) }

    if (isLoading.value) {
        errorCode.value = null
        LoadingIndicator()
    }

    if (errorCode.value != null) {
        val message = if (errorCode.value == ErrorCode.NETWORK.code)
            stringResource(id = R.string.network_error)
        else
            stringResource(id = R.string.source_error)
        ErrorMessage(message = message) {
            isLoading.value = true
            webView?.reload()
        }
    }
    if (errorCode.value == null) {
        Column(modifier = Modifier.fillMaxSize()) {
            if (!isLoading.value) {
                WebViewHeader(currentUrl.value){
                    webView?.scrollTo(0,0)
                }
            }
            AndroidView(
                modifier = Modifier.zIndex(1F),
                factory = {
                    WebView(context).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        webViewClient = CustomWebViewClient(
                            startURL = page.url,
                            changeLoading = { value ->
                                isLoading.value = value
                            },
                            changeErrorCode = { code ->
                                errorCode.value = code
                            },
                            changeCurrentUrl = { url ->
                                currentUrl.value = url
                            },
                            style = page.contentStyle
                        )
                        webChromeClient = object : WebChromeClient() {
                            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                                loadingPercent.value = newProgress
                            }
                        }
                        settings.cacheMode = page.cacheMode
                        settings.javaScriptEnabled = true
                        settings.loadWithOverviewMode = true
                        settings.useWideViewPort = true
                        webView = this

                        setDownloadListener { url, _, _, _, _ ->
                            browserIntent(url = url, context = context)
                        }

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && isDarkTheme) {
                            settings.forceDark = WebSettings.FORCE_DARK_ON
                        }
                        loadUrl(page.url)
                    }
                }, update = {
                    webView = it
                })
        }

        BackHandler(enabled = !currentUrl.value.startsWith(page.url)) {
            webView?.goBack()
        }
    }
}

@Composable
fun WebViewHeader(url: String, onUpClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        val context = LocalContext.current
        BarButton(icon = Icons.Default.Info, description = "to source") {
            browserIntent(url = url, context = context)
        }
        BarButton(icon = Icons.Default.KeyboardArrowUp, description = "to top") {
            onUpClick()
        }
    }
}

@Composable
fun BarButton(icon: ImageVector, description: String, onClick: () -> Unit) {
    Icon(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .size(30.dp),
        imageVector = icon,
        contentDescription = description
    )
}

@Destination
@Composable
fun ContactsWebView() = WebViewScreen(Page.PHONE)

@Destination
@Composable
fun PersonalAreaWebView() = WebViewScreen(Page.PERSONAL_AREA)

fun browserIntent(url: String, context: Context) {
    val i = Intent(Intent.ACTION_VIEW)
    try {
        i.data = Uri.parse(url)
    } catch (e: RuntimeException) {
        Toast.makeText(context, R.string.invalid_link, Toast.LENGTH_LONG).show()
    }
    try {
        context.startActivity(i)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, R.string.invalid_browser_intent, Toast.LENGTH_LONG).show()
    }
}

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