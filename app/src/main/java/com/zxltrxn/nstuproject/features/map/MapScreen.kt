package com.zxltrxn.nstuproject.features.map

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState

import com.google.maps.android.compose.GoogleMap

import com.google.maps.android.compose.Marker
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.zxltrxn.nstuproject.R
import com.zxltrxn.nstuproject.destinations.WebViewScreenDestination
import com.zxltrxn.nstuproject.features.webView.browserIntent
import com.zxltrxn.nstuproject.ui.spacing


@Destination
@Composable
fun MapScreen(navigator: DestinationsNavigator) {
    val vm = hiltViewModel<MapViewModel>()
    var detailInfoFor by remember { mutableStateOf<Spot?>(null) }
    val startPosition = remember {
        CameraPositionState(
            CameraPosition(
                LatLng(54.987815, 82.906219),
                15.5f,
                0f,
                300f
            )
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = vm.state.properties,
            uiSettings = vm.state.uiSettings,
            cameraPositionState = startPosition,
        ) {
            vm.state.spots.forEach {
                MyMarker(
                    spot = it,
                    onInfoWindowClick = { detailInfoFor = it },
                    onInfoWindowClose = { detailInfoFor = null }
                )
            }
        }
        detailInfoFor?.let {
            DetailInfo(
                modifier = Modifier.align(Alignment.BottomCenter),
                spot = it,
                navigator = navigator
            ) {
                detailInfoFor = null
            }
        }
    }
}

@Composable
fun MyMarker(spot: Spot, onInfoWindowClick: () -> Unit, onInfoWindowClose: () -> Unit) {
    val title = "${stringResource(spot.type.descriptionId)} ${spot.num ?: ""}"
    Marker(
        position = LatLng(spot.lat, spot.lng),
        title = title,
        snippet = stringResource(id = R.string.press_to_detail),
        icon = BitmapDescriptorFactory.defaultMarker(spot.type.markerColor),
        onInfoWindowClick = { onInfoWindowClick() },
        onInfoWindowClose = { onInfoWindowClose() }
    )
}

@Composable
fun DetailInfo(
    modifier: Modifier = Modifier,
    spot: Spot,
    navigator: DestinationsNavigator,
    onClose: () -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(12.dp, 12.dp))
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .background(color = MaterialTheme.colors.background)
            .zIndex(2f)
            .clickable { },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(0.9f),
                style = MaterialTheme.typography.h5,
                text = getTitle(spot = spot)
            )
            Icon(
                modifier = Modifier

                    .size(30.dp)
                    .clickable {
                        onClose()
                    },
                imageVector = Icons.Default.Close,
                contentDescription = "close details"
            )
        }

        spot.description?.let {
            Text(
                style = MaterialTheme.typography.h6,
                text = it
            )
        }
        spot.link?.let {
            LinkText { browserIntent(it, context) }
        }
        spot.inAppLink?.let {
            LinkText {
                navigator.navigate(
                    direction = WebViewScreenDestination(
                        page = it
                    )
                )
            }
        }
    }
}

@Composable
fun LinkText(onClick: () -> Unit) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.spacing.small)
            .clickable { onClick() },
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h6,
        text = stringResource(R.string.detail_link),
        color = Color.Blue
    )
}

@Composable
fun getTitle(spot: Spot): String {
    var title = stringResource(spot.type.descriptionId)
    spot.num?.let {
        title += " №$it"
    }
    return title
}

//        onMapClick = {
//            val df = DecimalFormat("#.######")
//            df.roundingMode = RoundingMode.DOWN
//            Log.d("MapScreen", "lat = ${df.format(it.latitude)}, lng = ${df.format(it.longitude)}")
//        }