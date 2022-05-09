package com.zxltrxn.nstuproject.features.parsing.minimumPoints.presentation

import com.zxltrxn.nstuproject.features.parsing.minimumPoints.domain.model.PointsData

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