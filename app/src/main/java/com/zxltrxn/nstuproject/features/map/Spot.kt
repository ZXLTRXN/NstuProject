package com.zxltrxn.nstuproject.features.map

import com.zxltrxn.nstuproject.R

data class Spot(
    val type: Type,
    val num: Int,
    val lat: Double,
    val lng: Double
){
    val name: String get() = "$type $num"

    enum class Type(val descriptionId: Int){
        HOSTEL(R.string.hostel_type),
        CAMPUS(R.string.campus_type),
    }
}
