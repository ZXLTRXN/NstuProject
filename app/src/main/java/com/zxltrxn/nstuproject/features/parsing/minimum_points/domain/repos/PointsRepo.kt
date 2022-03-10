package com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.repos

import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.model.PointsData

interface PointsRepo {
    fun getPointsData():PointsData
}