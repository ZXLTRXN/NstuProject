package com.zxltrxn.nstuproject.features.parsing.minimum_points.data

import android.util.Log
import com.zxltrxn.nstuproject.features.Page
import com.zxltrxn.nstuproject.features.parsing.Parser
import com.zxltrxn.nstuproject.features.parsing.Resource
import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.model.PointsData
import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.model.PointsTable
import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.model.SubjectWithPoints
import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.PointsRepo
import com.zxltrxn.nstuproject.utils.Constants.TAG
import java.io.IOException
import java.lang.Exception

class PointsRepoImpl(private val parser: Parser<PointsData>): PointsRepo {
    override suspend fun getPointsData(): Resource<PointsData> {
        return try{
            Resource.Success(
                parser.execute(Page.MINIMUM_POINTS.url)
            )
        }
        catch (e:IOException){
            Resource.Error("Network error")
        }
        catch (e:Exception){
            Log.e(TAG, "getPointsData: ${e.message}")
            Resource.Error("${e.message}")
        }

    }
}
//PointsData(
//title = "Минимальные баллы 2022",
//tables = listOf(
//PointsTable("Минимальные баллы вступительных испытаний для лиц," +
//" поступающих на базе среднего образования" +
//" (ЕГЭ, общеобразовательные вступительные испытания):",
//listOf(
//SubjectWithPoints("русик", 40),
//SubjectWithPoints("История развития электроэнергетики", 40),
//SubjectWithPoints("Информационные технологии для технологической деятельности", 40),
//SubjectWithPoints("русик", 40),
//SubjectWithPoints("История развития электроэнергетики", 40),
//SubjectWithPoints("Информационные технологии для технологической деятельности", 40),
//)
//),
//PointsTable("Минимальные баллы вступительных испытаний для лиц," +
//" поступающих на базе высшего образования" +
//" (ЕГЭ, общеобразовательные вступительные испытания):",
//listOf(
//SubjectWithPoints("русик", 40),
//SubjectWithPoints("История развития электроэнергетики", 40),
//SubjectWithPoints("Информационные технологии для технологической деятельности", 40),
//)
//),
//PointsTable("Минимальные баллы вступительных испытаний для лиц," +
//" поступающих на базе высшего образования" +
//" (ЕГЭ, общеобразовательные вступительные испытания):",
//listOf(
//SubjectWithPoints("русик", 40),
//SubjectWithPoints("История развития электроэнергетики", 40),
//SubjectWithPoints("Информационные технологии для технологической деятельности", 40),
//)
//)
//)
//)