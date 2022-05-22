package com.zxltrxn.nstuproject.features.parsing.minimumPoints.domain

import com.zxltrxn.nstuproject.features.parsing.commonData.ParserRepo
import com.zxltrxn.nstuproject.features.parsing.commonDomain.Resource
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.domain.model.Points
import javax.inject.Inject

class GetPointsUseCase @Inject constructor(private val repo: ParserRepo) {
    suspend operator fun invoke(): Resource<Points> = repo.getPoints()
}