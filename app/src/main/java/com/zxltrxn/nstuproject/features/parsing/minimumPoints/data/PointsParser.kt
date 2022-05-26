package com.zxltrxn.nstuproject.features.parsing.minimumPoints.data

import com.zxltrxn.nstuproject.features.parsing.commonData.Parser
import com.zxltrxn.nstuproject.features.parsing.commonDomain.Resource
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.data.model.PointsData
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.data.model.PointsTable
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.data.model.SubjectData
import org.jsoup.nodes.Document

class PointsParser : Parser<PointsData>() {
    override suspend fun execute(url: String): Resource<PointsData> {

        val doc: Document = when(val res = getDocument(url)){
            is Resource.Error -> return res
            is Resource.Success -> res.data
        }

        val header = doc.select("div.page-title h1").text()

        val parentSelector = "div.col-9.col-sm-12"
        val res: List<PointsTable> = doc.select(parentSelector).run {
            select("p").first()?.remove()
            select("p").first()?.remove()

            val titles = select("$parentSelector > p").map { it.text() }

            val pTables = titles.map {
                PointsTable(title = it, items = listOf())
            }

            val tables = select("table")
            tables.forEachIndexed { index, t ->
                t.select("tr").first()?.remove()

                pTables[index].items = t.select("tr").map {
                    val row = it.select("td p")
                    SubjectData(
                        name = row.get(0).text(),
                        points = row.get(1).text().toInt()
                    )
                }
            }
            pTables
        }
        return Resource.Success(PointsData(title = header, tables = res))
    }
}