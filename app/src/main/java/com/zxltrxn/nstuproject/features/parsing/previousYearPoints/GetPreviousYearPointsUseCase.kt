package com.zxltrxn.nstuproject.features.parsing.previousYearPoints

import com.zxltrxn.nstuproject.features.parsing.commonDomain.ParserRepo
import com.zxltrxn.nstuproject.features.parsing.commonDomain.Resource
import com.zxltrxn.nstuproject.features.parsing.previousYearPoints.model.PreviousYearPoints
import javax.inject.Inject

class GetPreviousYearPointsUseCase @Inject constructor(private val repo: ParserRepo) {
    suspend operator fun invoke(): Resource<PreviousYearPoints> = repo.getPreviousYearPoints()
}