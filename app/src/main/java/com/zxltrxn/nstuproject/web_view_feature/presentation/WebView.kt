package com.zxltrxn.nstuproject.web_view_feature.presentation

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.zxltrxn.nstuproject.Constants.TAG


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(
    targetPage:Pages,
    allowedUrlHost:String = "www.nstu.ru",
    cache:CacheModes = CacheModes.CACHE,
    isJSEnabled:Boolean = true,
    isFileLoadingEnabled:Boolean = true
){
    val context = LocalContext.current

    AndroidView(factory = {
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = object:WebViewClient(){
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    Log.d(TAG, "shouldOverrideUrlLoading: ${request?.url?.host}")
                    return request?.url?.host !=  allowedUrlHost
                }
            }

            settings.cacheMode = cache.mode
            settings.javaScriptEnabled = isJSEnabled

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                settings.forceDark = WebSettings.FORCE_DARK_AUTO
            }
            loadUrl(targetPage.url)
        }
    })
}


