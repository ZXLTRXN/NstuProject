package com.zxltrxn.nstuproject.features.parsing.plan.domain.model

data class Direction(
    var name: String,
    var code: String,
    var level: String,

    var budget: String? = null,
    var contract: String? = null,
    var quota: String? = null,
    var target: String? = null,
    var competitiveGroup: String,
    var accreditationPeriod: String,
    var special: String? = null
)
