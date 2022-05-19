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
fun AchievementsScreen(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier
) {
    Column() {
        Text(
            modifier = modifier.clickable {
                navigator.navigate(
                    direction = WebViewScreenDestination(
                        page = Page.INDIVIDUAL_ACHIEVEMENTS
                    )
                )
            },
            text = "Индивидуальные достижения"
        )
        Text(
            modifier = modifier.clickable {
                navigator.navigate(
                    direction = WebViewScreenDestination(
                        page = Page.SPECIAL_RIGHTS_FOR_WINNERS
                    )
                )
            },
            text = "Особые права, предоставляемые победителям и призерам олимпиад"
        )
    }
}