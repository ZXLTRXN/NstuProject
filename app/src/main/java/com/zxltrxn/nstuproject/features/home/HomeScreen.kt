package com.zxltrxn.nstuproject.features.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.zxltrxn.nstuproject.features.destinations.WebViewScreenDestination
import com.zxltrxn.nstuproject.features.Page
import com.zxltrxn.nstuproject.features.destinations.RecruitingPlanScreenDestination
import com.zxltrxn.nstuproject.features.destinations.PointsScreenDestination


@Destination(start = true)
@Composable
fun HomeScreen(
    navigator:DestinationsNavigator
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text(
            modifier = Modifier.clickable{
                navigator.navigate(direction = WebViewScreenDestination(
                    url = Page.RECRUITING_PLAN.url
                ))
            },
            text = "криво")
        Text(
            modifier = Modifier.clickable{
                navigator.navigate(direction = RecruitingPlanScreenDestination())
            },
            text = "план набора")
        Text(
            modifier = Modifier.clickable{
                navigator.navigate(direction = PointsScreenDestination())
            },
            text = "минимальные баллы")
        Text(
            modifier = Modifier.clickable{
                navigator.navigate(direction = WebViewScreenDestination(
                    url = Page.PERSONAL_AREA.url
                ))
            },
            text = "интент")
        Text(
            modifier = Modifier.clickable{
                navigator.navigate(direction = WebViewScreenDestination(
                    url = Page.EDUCATIONAL_PLANS.url
                ))
            },
            text = "медленно и скачка")
        Text(
            modifier = Modifier.clickable{
                navigator.navigate(direction = WebViewScreenDestination(
                    url = Page.MINIMUM_POINTS.url
                ))
            },
            text = "нормально")
    }
}