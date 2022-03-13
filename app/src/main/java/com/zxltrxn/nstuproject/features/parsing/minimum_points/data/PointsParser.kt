package com.zxltrxn.nstuproject.features.parsing.minimum_points.data

import com.zxltrxn.nstuproject.features.Page
import com.zxltrxn.nstuproject.features.parsing.Parser
import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.model.PointsData
import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.model.PointsTable
import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.model.SubjectWithPoints
import com.zxltrxn.nstuproject.features.parsing.minimum_points.presentation.SubjectRow
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

class PointsParser : Parser<PointsData>() {
    override suspend fun execute(url:String):PointsData{
        val doc:Document = connect(url) ?: throw IOException("Network error")

        val header = doc.select("div.page-title h1").text()

        val parentSelector = "div.col-9.col-sm-12"
        val res:List<PointsTable> = doc.select(parentSelector).run{
            select("p").first()?.remove()
            select("p").first()?.remove()

            val titles = select("$parentSelector > p").map{ it.text() }

            val pTables = titles.map {
                PointsTable(title = it,items = listOf())
            }

            val tables = select("table")
            tables.forEachIndexed { index,t->
                t.select("tr").first()?.remove()

                pTables[index].items = t.select("tr").map {
                    val row = it.select("td p")
                    SubjectWithPoints(subjectName = row.get(0).text(),
                        points = row.get(1).text().toInt())
                }
            }
            pTables
        }
        return PointsData(title = header,tables = res)
    }
}