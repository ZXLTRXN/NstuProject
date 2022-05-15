package com.zxltrxn.nstuproject.features.parsing.commonDomain

import android.content.Context
import androidx.annotation.StringRes

sealed interface LocalizeString {
    data class Resource(@StringRes val resId: Int) : LocalizeString
    data class Raw(val value: String) : LocalizeString

    fun getString(context: Context): String =
        when (this) {
            is Raw -> this.value
            is Resource -> context.getString(this.resId)
        }
}
