package com.zxltrxn.nstuproject

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.zxltrxn.nstuproject.destinations.DirectionDestination

data class MenuItem(
    @StringRes val titleId: Int,
    @DrawableRes val imageId: Int,
    val destination: DirectionDestination
)