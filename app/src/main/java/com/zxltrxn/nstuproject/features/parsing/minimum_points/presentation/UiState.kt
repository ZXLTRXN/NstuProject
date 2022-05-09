package com.zxltrxn.nstuproject.features.parsing.minimum_points.presentation

import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.model.PointsData

data class UiState(
    val isLoading:Boolean = false,
    val error:Error? = null,
    val data:PointsData = PointsData(title = "", tables = listOf())
){
    sealed class Error{
        object NetworkError:Error()
        object SourceError: Error()
    }
}