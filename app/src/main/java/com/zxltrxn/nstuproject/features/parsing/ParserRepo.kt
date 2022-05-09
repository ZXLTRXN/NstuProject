package com.zxltrxn.nstuproject.features.parsing

import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.model.PointsData

interface ParserRepo {
    suspend fun getPointsData(): Resource<PointsData>
}