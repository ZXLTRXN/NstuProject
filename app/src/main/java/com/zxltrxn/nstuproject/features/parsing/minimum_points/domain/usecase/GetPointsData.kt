package com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.usecase

import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.model.PointsData
import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.model.PointsTable
import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.repos.PointsRepo

class GetPointsData(private val repo:PointsRepo) {
    operator fun invoke():PointsData = repo.getPointsData()
}