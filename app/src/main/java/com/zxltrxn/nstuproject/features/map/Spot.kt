package com.zxltrxn.nstuproject.features.map

import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.zxltrxn.nstuproject.R
import com.zxltrxn.nstuproject.features.Page

data class Spot(
    val type: Type,
    val num: Int?,
    val lat: Double,
    val lng: Double,
    val description: String? = null,
    val link: String? = null,
    val inAppLink: Page? = null
) {
    enum class Type(
        val descriptionId: Int,
        val markerColor: Float = BitmapDescriptorFactory.HUE_VIOLET
    ) {
        HOSTEL(R.string.hostel_type, BitmapDescriptorFactory.HUE_ORANGE),
        CAMPUS(R.string.campus_type, BitmapDescriptorFactory.HUE_CYAN),
        POLYCLINIC(R.string.polyclinic, BitmapDescriptorFactory.HUE_RED),
        PROPHYLAXIS(R.string.prophylaxis, BitmapDescriptorFactory.HUE_RED),
        PALACE(R.string.sport_palace, BitmapDescriptorFactory.HUE_BLUE),
        COMPLEX(R.string.sport_complex, BitmapDescriptorFactory.HUE_BLUE),
        SPORT_GROUND(R.string.sport_ground, BitmapDescriptorFactory.HUE_BLUE),
        LIBRARY(R.string.library, BitmapDescriptorFactory.HUE_YELLOW),
        CULTURAL(R.string.cultural_center, BitmapDescriptorFactory.HUE_YELLOW),
        INCUBATOR(R.string.business_incubator),
        BOWLING(R.string.bowling)
    }
}
