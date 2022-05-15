package com.zxltrxn.nstuproject.features.parsing.commonData

import com.zxltrxn.nstuproject.features.parsing.commonDomain.Resource
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.domain.model.PointsData

interface ParserRepo {
    suspend fun getPointsData(): Resource<PointsData>
}