package com.zxltrxn.nstuproject.features.parsing.commonData

import com.zxltrxn.nstuproject.R
import com.zxltrxn.nstuproject.features.parsing.commonDomain.ErrorCode
import com.zxltrxn.nstuproject.features.parsing.commonDomain.LocalizeString
import com.zxltrxn.nstuproject.features.parsing.commonDomain.Resource
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

abstract class Parser<T> {
    protected fun getDocument(url: String): Resource<Document> {
        val client = OkHttpClient()
        val request: Request = Request.Builder()
            .url(url)
            .build()
        val response = try {
            client.newCall(request).execute()
        } catch (e: IOException) {
            return Resource.Error(
                LocalizeString.Resource(R.string.network_error),
                ErrorCode.NETWORK
            )
        }

        return if (response.isSuccessful && response.body != null) {
            val data: String = response.body!!.string()
            Resource.Success(Jsoup.parse(data))
        } else return Resource.Error(LocalizeString.Resource(R.string.source_error))
    }

    abstract suspend fun execute(url: String): Resource<T>
}