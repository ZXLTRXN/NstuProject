package com.zxltrxn.nstuproject.features.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun MapScreen() {
    val vm = hiltViewModel<MapViewModel>()
    val uiSettings = remember { MapUiSettings(zoomControlsEnabled = false) }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = vm.state.properties,
        uiSettings = uiSettings,
        onMapClick = { }
    ) {
        vm.state.spots.forEach { spot ->

            Marker(
                position = LatLng(spot.lat, spot.lng),
                title = spot.num.toString(),
                onClick = {
                    it.showInfoWindow()
                    true
                },
                icon = BitmapDescriptorFactory.defaultMarker(
                    when(spot.type){
                        Spot.Type.HOSTEL -> BitmapDescriptorFactory.HUE_ORANGE
                        Spot.Type.CAMPUS -> BitmapDescriptorFactory.HUE_ORANGE
                    }
                )
            )

        }
    }
}