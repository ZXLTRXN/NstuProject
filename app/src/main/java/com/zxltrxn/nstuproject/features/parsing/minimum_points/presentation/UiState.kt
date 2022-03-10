package com.zxltrxn.nstuproject.features.parsing.minimum_points.presentation

import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.model.PointsData

data class UiState(
    val isLoading:Boolean = false,
    val error:String? = null, //error class better
    val data:PointsData? = null
)