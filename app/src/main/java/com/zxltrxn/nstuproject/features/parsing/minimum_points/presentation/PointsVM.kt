package com.zxltrxn.nstuproject.features.parsing.minimum_points.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.GetPointsData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PointsVM @Inject constructor(
    private val getData: GetPointsData
):ViewModel() {
    private val _uiState = mutableStateOf(UiState(isLoading = true))
    val uiState: State<UiState> = _uiState

    init{
        _uiState.value = UiState(data = getData())
    }
}