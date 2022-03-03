package com.zxltrxn.nstuproject.features.web_view.presentation

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
    url:String?,
    allowedUrlHost:List<String> = listOf("www.nstu.ru","ciu.nstu.ru"),
    cacheMode:Int = CacheMode.CACHE.value,
    isJSEnabled:Boolean = true,
    isFileLoadingEnabled:Boolean = false
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
                    return request?.url?.host !in allowedUrlHost
                }
            }

            settings.cacheMode = cacheMode
            settings.javaScriptEnabled = isJSEnabled

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                settings.forceDark = WebSettings.FORCE_DARK_AUTO
            }
            url?.let{
                loadUrl(it)
            }
        }
    })
}


