package com.zxltrxn.nstuproject.navigation

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screen(val route:String){
    object Home : Screen("home")
    object Contacts : Screen("contacts")
    object WebView : Screen("webView")

    fun withArgs(vararg args:String):String =
        buildString {
            append(route)
            args.forEach {
                val encodedUrl = URLEncoder.encode(it, StandardCharsets.UTF_8.toString())
                append("/$encodedUrl")
            }
        }
}
