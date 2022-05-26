package com.zxltrxn.nstuproject.features.parsing.plan.data

import com.zxltrxn.nstuproject.features.parsing.commonData.Parser
import com.zxltrxn.nstuproject.features.parsing.commonDomain.Resource
import com.zxltrxn.nstuproject.features.parsing.plan.data.model.DirectionData
import com.zxltrxn.nstuproject.features.parsing.plan.data.model.FacultyData
import com.zxltrxn.nstuproject.features.parsing.plan.data.model.FormTable
import com.zxltrxn.nstuproject.features.parsing.plan.data.model.PlanData
import org.jsoup.nodes.Document
import java.io.IOException

class PlanParser : Parser<PlanData>() {
    val TAG = javaClass.simpleName

    override suspend fun execute(url: String): Resource<PlanData> {

        val doc: Document = when(val res = getDocument(url)){
            is Resource.Error -> return res
            is Resource.Success -> res.data
        }

        val title = doc.select("div.page-title h1").text()
        val tables = doc.select("table")
        val formsTitles = doc.select("a.enrollee-plan__anchor").map { it.text() }

        val forms: MutableList<FormTable> = mutableListOf()

        tables.forEachIndexed { tableIndex, table ->
            val faculties: MutableList<FacultyData> = mutableListOf()

            var facultyName: String? = null
            var directions: MutableList<DirectionData> = mutableListOf()

            table.select("tr").map { row ->
                if (row.hasClass("enrollee-plan__faculty-total")) {
                    faculties.add(FacultyData(name = facultyName!!, directions = directions))
                    directions = mutableListOf()
                    facultyName = null
                } else {
                    var direction: DirectionData? = null
                    row.select("td").mapIndexed { index, col ->
                        if (col.hasClass("enrollee-plan__faculty-header"))
                            facultyName = col.text()
                        else {
                            when (index) {
                                0 -> {
                                    direction = DirectionData(
                                        code = col.select("span.enrollee-plan__table-code").text(),
                                        name = col.select("span.enrollee-plan__table-direction")
                                            .text(),
                                        level = col.select("span.enrollee-plan__table-profile.text-gray")
                                            .text(),
                                        special = col.select("span.enrollee-plan__table-profile b")
                                            .text()
                                    )
                                }
                                1 -> direction?.budget = col.text()
                                2 -> direction?.contract = col.text()
                                3 -> direction?.quota = col.text()
                                4 -> direction?.target = col.text()
                                5 -> direction?.competitiveGroup = col.text()
                                6 -> {
                                    direction?.accreditationPeriod = col.text()
                                    directions.add(direction!!)
                                }
                                else -> {}
                            }
                        }
                    }
                }
            }
            forms.add(FormTable(title = formsTitles[tableIndex], faculties = faculties))
        }
        return Resource.Success(PlanData(title, forms))
    }
}