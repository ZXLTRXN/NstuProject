package com.zxltrxn.nstuproject.features.parsing.commonPresentation

import com.zxltrxn.nstuproject.features.parsing.commonDomain.LocalizeString


sealed interface UiState<out T> {
    object IsLoading : UiState<Nothing>
    data class Error(val message: LocalizeString) : UiState<Nothing>
    data class Loaded<T>(val data: T) : UiState<T>
}