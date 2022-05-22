package com.zxltrxn.nstuproject.features.parsing.plan.domain.model

data class Direction(
    val name: String,
    val code: String,
    val level: String,

    val budget: String? = null,
    val contract: String? = null,
    val quota: String? = null,
    val target: String? = null,
    val competitiveGroup: String,
    val accreditationPeriod: String,
    val special: String? = null
)
