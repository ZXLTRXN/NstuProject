package com.zxltrxn.nstuproject.features.webView

import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.MutableState

class CustomWebViewClient(
    private val isLoading: MutableState<Boolean>,
    private val backEnabled: MutableState<Boolean>
) : WebViewClient() {
    private val TAG = javaClass.simpleName
    private val urlHostWithIntent: List<String> = listOf("ciu.nstu.ru")
    private val allowedUrlHost: List<String> = listOf(
        "www.nstu.ru", "www.avtf.nstu.ru", "www.fla.nstu.ru", "www.mtf.nstu.ru",
        "www.fma.nstu.ru", "www.fpmi.nstu.ru", "www.ref.nstu.ru", "www.ftf.nstu.ru",
        "www.fen.nstu.ru", "www.fb.nstu.ru", "www.fgo.nstu.ru", "www.istr.nstu.ru"
    )

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        backEnabled.value = view?.canGoBack() ?: false
        if (!isLoading.value) isLoading.value = true
        view?.visibility = View.INVISIBLE
        super.onPageStarted(view, url, favicon)
    }

    override fun shouldOverrideUrlLoading(
        view: WebView?,
        request: WebResourceRequest?
    ): Boolean {
        Log.d(TAG, "shouldOverrideUrlLoading: ${request?.url?.host}")
        return if (request?.url?.host in urlHostWithIntent) {
            Log.d(TAG, "shouldOverrideUrlLoading: intent")
            view?.context?.startActivity(
                Intent(Intent.ACTION_VIEW, request?.url)
            )
            true
        } else {
            request?.url?.host !in allowedUrlHost
        }
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        view?.loadUrl(withJS())
        super.onPageFinished(view, url)
        isLoading.value = false
        view?.smoothShow()
    }

    private fun withJS(
        removeOddBlocks: Boolean = true,
        removeShadowedBorder: Boolean = true
    ): String {
        var body = if (removeOddBlocks) oddBlocks else ""
        body += if (removeShadowedBorder) shadowedBorder else ""
        return """javascript:(function() {
            $body
            })()"""
    }

    companion object {
        const val oddBlocks: String = """
            document.getElementsByClassName('header-mobile')[0].style.display="none";
            document.getElementsByClassName('breadcrumbs')[0].style.display="none";
            document.getElementsByClassName('page-footer')[0].style.display="none";
            """

        const val shadowedBorder = """
            var node = document.createElement('style');
            node.type = 'text/css';
            node.innerHTML = '.page-wrapper {
                box-shadow: none;
                -webkit-box-shadow: none;
            }';
            document.head.appendChild(node);
            """
    }
}