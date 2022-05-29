package com.zxltrxn.nstuproject.features.webView

import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.MutableState
import com.zxltrxn.nstuproject.features.parsing.commonDomain.ErrorCode

class CustomWebViewClient(
    private val changeLoading: (Boolean) -> Unit,
    private val changeBackEnabled: (Boolean) -> Unit,
    private val changeErrorCode: (Int?) -> Unit,
    private val style: ContentStyle
) : WebViewClient() {
    private val TAG = javaClass.simpleName

    private val allowedUrlHost: List<String> = listOf(
        "www.nstu.ru", "www.avtf.nstu.ru", "www.fla.nstu.ru", "www.mtf.nstu.ru",
        "www.fma.nstu.ru", "www.fpmi.nstu.ru", "www.ref.nstu.ru", "www.ftf.nstu.ru",
        "www.fen.nstu.ru", "www.fb.nstu.ru", "www.fgo.nstu.ru", "www.istr.nstu.ru"
    )

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        changeBackEnabled(view?.canGoBack() ?: false)
        changeLoading(true)
        view?.visibility = View.INVISIBLE
        super.onPageStarted(view, url, favicon)
    }

    override fun shouldOverrideUrlLoading(
        view: WebView?,
        request: WebResourceRequest?
    ): Boolean {
        Log.d(TAG, "shouldOverrideUrlLoading: ${request?.url?.host}")
        return if (request?.url?.host !in allowedUrlHost) {
            view?.context?.startActivity(
                Intent(Intent.ACTION_VIEW, request?.url)
            )
            true
        } else {
            false
        }
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        view?.loadUrl(style.applyStyleInJS())
        super.onPageFinished(view, url)
        changeLoading(false)
        view?.smoothShow()
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)
        val code = when (error?.errorCode) {
            ERROR_HOST_LOOKUP -> ErrorCode.NETWORK.code
            ERROR_CONNECT -> null
            else -> ErrorCode.SOURCE_UNAVAILABLE.code
        }
        changeErrorCode(code)
    }
}