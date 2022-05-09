package com.zxltrxn.nstuproject.features.parsing.minimumPoints.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zxltrxn.nstuproject.features.parsing.Resource
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.domain.GetPointsDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PointsVM @Inject constructor(
    private val getData: GetPointsDataUseCase
):ViewModel() {
    private val _uiState = mutableStateOf(UiState())
    val uiState: State<UiState> = _uiState

    init{
        fetchData()
    }

    private fun fetchData(){
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch() {
            when(val resource = getData()){
                is Resource.Success ->{
                    _uiState.value = resource.data?.let{
                        UiState(data = it)
                    } ?: UiState()
                }
                is Resource.Error ->{
                    _uiState.value = UiState(error = UiState.Error.NetworkError)
                }
            }
        }
    }
}