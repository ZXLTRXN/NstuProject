package com.zxltrxn.nstuproject.features.parsing.previousYearPoints.model

import kotlinx.serialization.Serializable

@Serializable
data class Direction(
    val name: String,
    val profile: String,
    val budget: String? = null,
    val contract: String? = null
)
