package com.zxltrxn.nstuproject.features.parsing.commonDomain

import com.zxltrxn.nstuproject.features.parsing.commonDomain.Resource
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.domain.model.Points
import com.zxltrxn.nstuproject.features.parsing.plan.domain.model.Plan
import com.zxltrxn.nstuproject.features.parsing.previousYearPoints.model.PreviousYearPoints

interface ParserRepo {
    suspend fun getPoints(): Resource<Points>
    suspend fun getPlan(): Resource<Plan>
    suspend fun getPreviousYearPoints(): Resource<PreviousYearPoints>
}