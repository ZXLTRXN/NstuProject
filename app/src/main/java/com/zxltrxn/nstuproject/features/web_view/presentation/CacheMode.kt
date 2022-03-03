package com.zxltrxn.nstuproject.features.web_view.presentation

import android.webkit.WebSettings

enum class CacheMode(val value:Int) {
    NO_CACHE(WebSettings.LOAD_NO_CACHE),
    CACHE(WebSettings.LOAD_DEFAULT)
}