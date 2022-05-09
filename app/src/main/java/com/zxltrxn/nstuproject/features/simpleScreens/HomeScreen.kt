package com.zxltrxn.nstuproject.features.simpleScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.zxltrxn.nstuproject.features.destinations.WebViewScreenDestination
import com.zxltrxn.nstuproject.features.Page
import com.zxltrxn.nstuproject.features.destinations.AchievementsScreenDestination
import com.zxltrxn.nstuproject.features.destinations.PointsScreenDestination
import com.zxltrxn.nstuproject.features.destinations.RaitingScreenDestination


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
                navigator.navigate(direction = PointsScreenDestination())
            },
            text = "Минимальные баллы по ЕГЭ")
        Text(
            modifier = Modifier.clickable{
                navigator.navigate(direction = WebViewScreenDestination(
                    url = Page.SEARCH_BACHELORS_PROGRAMS.url
                ))
            },
            text = "Подобрать направление обучения")
        Spacer(modifier = Modifier.height(5.dp))

        Text(
            modifier = Modifier.clickable{
                navigator.navigate(direction = WebViewScreenDestination(
                    url = Page.HOSTEL.url
                ))
            },
            text = "Общежития")
        Text(
            modifier = Modifier.clickable{
                navigator.navigate(direction = AchievementsScreenDestination())
            },
            text = "Индивидуальные достижения")
        Spacer(modifier = Modifier.height(5.dp))

        Text(
            modifier = Modifier.clickable{
                navigator.navigate(direction = WebViewScreenDestination(
                    url = Page.CONTRACT.url
                ))
            },
            text = "Контракт")
        Text(
            modifier = Modifier.clickable{
                navigator.navigate(direction = WebViewScreenDestination(
                    url = Page.ACCEPTANCE_OF_DOCUMENTS.url
                ))
            },
            text = "Документы")
        Text(
            modifier = Modifier.clickable{
                navigator.navigate(direction = RaitingScreenDestination())
            },
            text = "Рейтинговые списки")
    }
}