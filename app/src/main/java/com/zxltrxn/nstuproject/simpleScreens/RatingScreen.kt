package com.zxltrxn.nstuproject.simpleScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.zxltrxn.nstuproject.destinations.WebViewScreenDestination
import com.zxltrxn.nstuproject.features.Page

@Destination
@Composable
fun RatingScreen(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier
) {
    Column() {
        Text(
            modifier = modifier.clickable {
                navigator.navigate(
                    direction = WebViewScreenDestination(
                        page = Page.RATING_LIST
                    )
                )
            },
            text = "Списки"
        )
    }
}