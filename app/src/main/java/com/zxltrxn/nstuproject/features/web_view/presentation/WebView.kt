package com.zxltrxn.nstuproject.features.web_view.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.util.Log
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
import androidx.lifecycle.ViewModelProvider
import com.ramcosta.composedestinations.annotation.Destination
import com.zxltrxn.nstuproject.Constants.TAG
import com.zxltrxn.nstuproject.common_composable.LoadingIndicator


@SuppressLint("SetJavaScriptEnabled")
@Destination
@Composable
fun WebViewScreen(
    url:String?,
    cacheMode:Int = WebSettings.LOAD_CACHE_ELSE_NETWORK
){
    val urlHostWithIntent:List<String> =  listOf("ciu.nstu.ru")
    val allowedUrlHost:List<String> = listOf(
    "www.nstu.ru", "www.avtf.nstu.ru", "www.fla.nstu.ru", "www.mtf.nstu.ru",
    "www.fma.nstu.ru", "www.fpmi.nstu.ru", "www.ref.nstu.ru", "www.ftf.nstu.ru",
    "www.fen.nstu.ru", "www.fb.nstu.ru", "www.fgo.nstu.ru", "www.istr.nstu.ru")

    var webView: WebView? = null
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()

    var backEnabled by remember { mutableStateOf(false) }
    var isLoading by remember{ mutableStateOf(true)}

    if(isLoading){
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

            webViewClient = object:WebViewClient(){

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    backEnabled = view?.canGoBack()?: false
                    if(!isLoading) isLoading = true
                    view?.visibility = View.INVISIBLE
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
                    }
                    else{
                        request?.url?.host !in  allowedUrlHost
                    }
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    view?.loadUrl("javascript:(function() { " +
                            "document.getElementsByClassName('header-mobile')[0].style.display=\"none\"; " +
                            "document.getElementsByClassName('breadcrumbs')[0].style.display=\"none\"; " +
                            "document.getElementsByClassName('page-footer')[0].style.display=\"none\"; " +
                            "})()")
                    isLoading = false
                    view?.visibility = View.VISIBLE
                }
            }
            settings.cacheMode = cacheMode
            settings.javaScriptEnabled = true
            webView = this

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && isDarkTheme) {
                settings.forceDark = WebSettings.FORCE_DARK_ON
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



//@SuppressLint("SetJavaScriptEnabled")
//@Destination
//@Composable
//fun WebViewScreen(
//    url:String?,
//    cacheMode:Int = WebSettings.LOAD_CACHE_ELSE_NETWORK
//){
//    val context = LocalContext.current
//    val factory = WebViewVM.Factory(context)
//
//    val viewModel = ViewModelProvider(context, factory).get(WebViewVM::class.java)
//
//
//}


//val dir: File = context.cacheDir
//if (dir.exists())
//Log.d(TAG, "shouldOverrideUrlLoading: yes Cache ${dir.listFiles()}")
//else
//Log.d(TAG, "shouldOverrideUrlLoading: noCache")