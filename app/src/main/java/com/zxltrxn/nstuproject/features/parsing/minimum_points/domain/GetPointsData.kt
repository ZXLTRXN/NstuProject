package com.zxltrxn.nstuproject.features.parsing.minimum_points.domain

import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.model.PointsData

class GetPointsData(private val repo: PointsRepo) {
    operator fun invoke():PointsData = repo.getPointsData()
}