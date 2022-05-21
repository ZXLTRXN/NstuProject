package com.zxltrxn.nstuproject.features.parsing.commonData

import com.zxltrxn.nstuproject.features.parsing.commonDomain.Resource
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.domain.model.PointsData
import com.zxltrxn.nstuproject.features.parsing.plan.data.model.PlanData

interface ParserRepo {
    suspend fun getPointsData(): Resource<PointsData>
    suspend fun getPlanData(): Resource<PlanData>
}