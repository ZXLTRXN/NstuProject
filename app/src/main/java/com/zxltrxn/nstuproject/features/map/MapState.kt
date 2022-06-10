package com.zxltrxn.nstuproject.features.map

import com.google.maps.android.compose.MapProperties

data class MapState(
    val properties: MapProperties = MapProperties(),
    val spots: List<Spot> = listOf(
        Spot(type = Spot.Type.CAMPUS, 6, 54.990113, 82.901126)
    )
)
