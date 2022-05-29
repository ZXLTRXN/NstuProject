package com.zxltrxn.nstuproject.features.parsing.commonData

import com.zxltrxn.nstuproject.R
import com.zxltrxn.nstuproject.features.Page
import com.zxltrxn.nstuproject.features.parsing.commonDomain.ErrorCode
import com.zxltrxn.nstuproject.features.parsing.commonDomain.LocalizeString
import com.zxltrxn.nstuproject.features.parsing.commonDomain.ParserRepo
import com.zxltrxn.nstuproject.features.parsing.commonDomain.Resource
import com.zxltrxn.nstuproject.features.parsing.commonDomain.map
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.data.model.PointsData
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.domain.model.Points
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.toPoints
import com.zxltrxn.nstuproject.features.parsing.plan.data.model.PlanData
import com.zxltrxn.nstuproject.features.parsing.plan.domain.model.Plan
import com.zxltrxn.nstuproject.features.parsing.plan.toPlan
import com.zxltrxn.nstuproject.features.parsing.previousYearPoints.model.PreviousYearPoints
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ParserRepoImpl @Inject constructor(
    private val assetManager: AssetManager,
    private val pointsParser: Parser<PointsData>,
    private val planParser: Parser<PlanData>
) : ParserRepo {

    override suspend fun getPoints(): Resource<Points> = withContext(Dispatchers.IO) {
        val res = pointsParser.execute(Page.MINIMUM_POINTS.url)
        return@withContext res.map { pointsData -> pointsData.toPoints() }
    }

    override suspend fun getPlan(): Resource<Plan> = withContext(Dispatchers.IO) {
        val res = planParser.execute(Page.RECRUITING_PLAN.url)
        return@withContext res.map { planData -> planData.toPlan() }
    }

    override suspend fun getPreviousYearPoints(): Resource<PreviousYearPoints> =
        withContext(Dispatchers.IO) {
            val json = assetManager.read("points2021.json")
            val error =
                Resource.Error(
                    LocalizeString.Resource(R.string.data_error),
                    ErrorCode.SERIALIZATION
                )
            return@withContext if (json == null) error
            else {
                try {
                    Resource.Success(Json.decodeFromString<PreviousYearPoints>(json))
                } catch (e: SerializationException) {
                    error
                }
            }
        }
}