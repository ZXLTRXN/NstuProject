package com.zxltrxn.nstuproject.features.parsing.plan.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zxltrxn.nstuproject.features.parsing.commonDomain.LocalizeString
import com.zxltrxn.nstuproject.features.parsing.commonDomain.Resource
import com.zxltrxn.nstuproject.features.parsing.plan.domain.GetPlanUseCase
import com.zxltrxn.nstuproject.features.parsing.plan.domain.model.Plan
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanViewModel @Inject constructor(
    private val getData: GetPlanUseCase
) : ViewModel() {
    private val _uiState: MutableState<UiState> = mutableStateOf(UiState.IsLoading)
    val uiState: State<UiState> = _uiState

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch() {
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

    sealed interface UiState {
        object IsLoading : UiState
        data class Error(val message: LocalizeString) : UiState
        data class Loaded(val data: Plan) : UiState
    }
}