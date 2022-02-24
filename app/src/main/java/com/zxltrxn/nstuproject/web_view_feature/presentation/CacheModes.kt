package com.zxltrxn.nstuproject.web_view_feature.presentation

import android.webkit.WebSettings

enum class CacheModes(val mode:Int) {
    NO_CACHE(WebSettings.LOAD_NO_CACHE),
    CACHE(WebSettings.LOAD_DEFAULT)
}