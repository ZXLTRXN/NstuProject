package com.zxltrxn.nstuproject.features.parsing

import com.zxltrxn.nstuproject.features.parsing.minimumPoints.domain.model.PointsData

interface ParserRepo {
    suspend fun getPointsData(): Resource<PointsData>
}