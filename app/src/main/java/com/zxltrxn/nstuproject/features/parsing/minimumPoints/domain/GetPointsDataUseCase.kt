package com.zxltrxn.nstuproject.features.parsing.minimumPoints.domain

import com.zxltrxn.nstuproject.features.parsing.commonData.ParserRepo
import com.zxltrxn.nstuproject.features.parsing.commonDomain.Resource
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.domain.model.PointsData
import javax.inject.Inject

class GetPointsDataUseCase @Inject constructor(private val repo: ParserRepo) {
    suspend operator fun invoke(): Resource<PointsData> = repo.getPointsData()
}