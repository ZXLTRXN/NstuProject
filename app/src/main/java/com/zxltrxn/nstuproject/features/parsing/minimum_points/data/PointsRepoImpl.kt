package com.zxltrxn.nstuproject.features.parsing.minimum_points.data

import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.model.PointsData
import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.model.PointsTable
import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.model.SubjectWithPoints
import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.PointsRepo

class PointsRepoImpl(): PointsRepo {
    override fun getPointsData(): PointsData {
        return PointsData(
            title = "Минимальные баллы 2022",
            tables = listOf(
                PointsTable("Минимальные баллы вступительных испытаний для лиц," +
                        " поступающих на базе среднего образования" +
                        " (ЕГЭ, общеобразовательные вступительные испытания):",
                    listOf(
                        SubjectWithPoints("русик", 40),
                        SubjectWithPoints("История развития электроэнергетики", 40),
                        SubjectWithPoints("Информационные технологии для технологической деятельности", 40),
                        SubjectWithPoints("русик", 40),
                        SubjectWithPoints("История развития электроэнергетики", 40),
                        SubjectWithPoints("Информационные технологии для технологической деятельности", 40),
                    )
                ),
                PointsTable("Минимальные баллы вступительных испытаний для лиц," +
                        " поступающих на базе высшего образования" +
                        " (ЕГЭ, общеобразовательные вступительные испытания):",
                    listOf(
                        SubjectWithPoints("русик", 40),
                        SubjectWithPoints("История развития электроэнергетики", 40),
                        SubjectWithPoints("Информационные технологии для технологической деятельности", 40),
                    )
                ),
                PointsTable("Минимальные баллы вступительных испытаний для лиц," +
                        " поступающих на базе высшего образования" +
                        " (ЕГЭ, общеобразовательные вступительные испытания):",
                    listOf(
                        SubjectWithPoints("русик", 40),
                        SubjectWithPoints("История развития электроэнергетики", 40),
                        SubjectWithPoints("Информационные технологии для технологической деятельности", 40),
                    )
                )
            )
        )
    }
}