package com.zxltrxn.nstuproject.features.parsing.previousYearPoints.model

import kotlinx.serialization.Serializable

@Serializable
data class PreviousYearPoints(
    val title: String,
    val forms: List<Form>
)
