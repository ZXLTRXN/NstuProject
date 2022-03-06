package com.zxltrxn.nstuproject.features.web_view.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.ramcosta.composedestinations.annotation.Destination
import com.zxltrxn.nstuproject.Constants.TAG


@SuppressLint("SetJavaScriptEnabled")
@Destination
@Composable
fun WebViewScreen(
    url:String?,
    isFileLoadingEnabled:Boolean = false,
    allowedUrlHost:List<String> = listOf(
        "www.nstu.ru", "www.avtf.nstu.ru", "www.fla.nstu.ru", "www.mtf.nstu.ru",
        "www.fma.nstu.ru", "www.fpmi.nstu.ru", "www.ref.nstu.ru", "www.ftf.nstu.ru",
        "www.fen.nstu.ru", "www.fb.nstu.ru", "www.fgo.nstu.ru", "www.istr.nstu.ru"
    ),
    urlHostWithIntent:List<String> =  listOf("ciu.nstu.ru"),
    cacheMode:Int = CacheMode.NO_CACHE.value
){
    val context = LocalContext.current

    var backEnabled by remember { mutableStateOf(false) }
    var webView: WebView? = null

    AndroidView(factory = {
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = object:WebViewClient(){

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    backEnabled = view?.canGoBack()?: false
                }

                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    Log.d(TAG, "shouldOverrideUrlLoading: ${request?.url?.host}")
                    return if (request?.url?.host in urlHostWithIntent){
                        view?.context?.startActivity(
                            Intent(Intent.ACTION_VIEW, request?.url)
                        )
                        true
                    }else{
                        request?.url?.host !in  allowedUrlHost
                    }
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    view?.loadUrl("javascript:(function() { " +
                            "document.getElementsByClassName('header-mobile')[0].style.display=\"none\"; " +
                            "document.getElementsByClassName('breadcrumbs')[0].style.display=\"none\"; " +
                            "document.getElementsByClassName('page-footer')[0].style.display=\"none\"; " +
                            "})()")
                }
            }
            settings.cacheMode = cacheMode
            settings.javaScriptEnabled = true
            settings.allowFileAccess = isFileLoadingEnabled
            webView = this

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                settings.forceDark = WebSettings.FORCE_DARK_AUTO
            }

            url?.let{
                loadUrl(it)
            }
        }
    },update ={
        webView = it
    })

    BackHandler(enabled = backEnabled) {
        webView?.goBack()
    }
}


