package com.zxltrxn.nstuproject.features.parsing.minimum_points.data

import com.zxltrxn.nstuproject.features.Page
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

// https://jsoup.org/cookbook/extracting-data/selector-syntax
class PointsParser {
    private fun connect(url:String): Document?{
        return try{
            val client = OkHttpClient()
            val request: Request = Request.Builder()
                .url(url)
                .build()

            val response = client.newCall(request).execute()
            val data:String = response.body?.string() ?: throw IOException()
            Jsoup.parse(data)
        }catch(e:IOException){
            null
        }
    }

    fun execute(url:String = Page.MINIMUM_POINTS.url){
        val doc:Document = connect(url) ?: throw IOException("Network error")


        val header = doc.select("div.page-title h1").text()
        println("execute: $header")

        val parentSelector = "div.col-9.col-sm-12"
        doc.select(parentSelector).run{
            select("p").first()?.remove()
            select("p").first()?.remove()

            val titles = select("$parentSelector > p").map{ it.text() }
            println("execute: ${titles}")

//            val tables = select()
        }


    }

}