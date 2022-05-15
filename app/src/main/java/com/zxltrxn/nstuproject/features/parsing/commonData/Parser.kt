package com.zxltrxn.nstuproject.features.parsing.commonData

import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

// https://jsoup.org/cookbook/extracting-data/selector-syntax
abstract class Parser<T> {
    protected fun connect(url: String): Document? {
        return try {
            val client = OkHttpClient()
            val request: Request = Request.Builder()
                .url(url)
                .build()

            val response = client.newCall(request).execute()
            val data: String = response.body?.string() ?: throw IOException()
            Jsoup.parse(data)
        } catch (e: IOException) {
            null
        }
    }

    abstract suspend fun execute(url: String): T
}