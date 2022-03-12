package com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.usecase

import com.zxltrxn.nstuproject.features.parsing.Resource
import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.PointsRepo
import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.model.PointsData

class GetPointsData(private val repo: PointsRepo) {
    suspend operator fun invoke(): Resource<PointsData> = repo.getPointsData()
}