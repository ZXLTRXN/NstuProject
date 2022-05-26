package com.zxltrxn.nstuproject.features.parsing.previousYearPoints.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Form(
    @SerialName("form") val title: String,
    val faculties: List<Faculty>
)
