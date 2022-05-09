package com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.usecase

import com.zxltrxn.nstuproject.features.parsing.ParserRepo
import com.zxltrxn.nstuproject.features.parsing.Resource
import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.model.PointsData
import javax.inject.Inject

class GetPointsData @Inject constructor(private val repo: ParserRepo) {
    suspend operator fun invoke(): Resource<PointsData> = repo.getPointsData()
}