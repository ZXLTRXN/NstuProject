package com.zxltrxn.nstuproject.features.parsing.commonData

import com.zxltrxn.nstuproject.features.parsing.commonDomain.Resource
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.domain.model.Points
import com.zxltrxn.nstuproject.features.parsing.plan.domain.model.Plan

interface ParserRepo {
    suspend fun getPoints(): Resource<Points>
    suspend fun getPlan(): Resource<Plan>
}