package com.zxltrxn.nstuproject.features.parsing.previousYearPoints.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Faculty(
    @SerialName("faculty") val name: String,
    val directions: List<Direction>
)
