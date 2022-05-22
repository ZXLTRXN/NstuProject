package com.zxltrxn.nstuproject.features.parsing.plan.domain

import com.zxltrxn.nstuproject.features.parsing.commonData.ParserRepo
import com.zxltrxn.nstuproject.features.parsing.commonDomain.Resource
import com.zxltrxn.nstuproject.features.parsing.plan.domain.model.Plan
import javax.inject.Inject

class GetPlanUseCase @Inject constructor(private val repo: ParserRepo) {
    suspend operator fun invoke(): Resource<Plan> = repo.getPlan()
}