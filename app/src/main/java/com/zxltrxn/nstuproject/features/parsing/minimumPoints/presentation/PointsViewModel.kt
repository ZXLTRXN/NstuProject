package com.zxltrxn.nstuproject.features.parsing.minimumPoints.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zxltrxn.nstuproject.features.parsing.commonDomain.LocalizeString
import com.zxltrxn.nstuproject.features.parsing.commonDomain.Resource
import com.zxltrxn.nstuproject.features.parsing.commonPresentation.UiState
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.domain.GetPointsUseCase
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.domain.model.Points
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PointsViewModel @Inject constructor(
    private val getData: GetPointsUseCase
) : ViewModel() {
    private val _uiState: MutableState<UiState<Points>> = mutableStateOf(UiState.IsLoading)
    val uiState: State<UiState<Points>> = _uiState

    init {
        fetchData()
    }

    fun retry(){
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch() {
            _uiState.value = UiState.IsLoading
            when (val res = getData()) {
                is Resource.Success -> {
                    _uiState.value = UiState.Loaded(data = res.data)
                }
                is Resource.Error -> {
                    _uiState.value = UiState.Error(message = res.message)
                }
            }
        }
    }

}