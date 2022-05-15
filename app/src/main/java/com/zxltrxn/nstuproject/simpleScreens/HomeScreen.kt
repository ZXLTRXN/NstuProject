package com.zxltrxn.nstuproject.simpleScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.zxltrxn.nstuproject.destinations.WebViewScreenDestination
import com.zxltrxn.nstuproject.features.Page
import com.zxltrxn.nstuproject.destinations.AchievementsScreenDestination
import com.zxltrxn.nstuproject.destinations.PointsScreenDestination
import com.zxltrxn.nstuproject.destinations.RaitingScreenDestination


@Destination(start = true)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {
        Text(
            modifier = Modifier.clickable {
                navigator.navigate(direction = PointsScreenDestination())
            },
            text = "Минимальные баллы по ЕГЭ"
        )
        Text(
            modifier = Modifier.clickable {
                navigator.navigate(
                    direction = WebViewScreenDestination(
                        url = Page.SEARCH_BACHELORS_PROGRAMS.url
                    )
                )
            },
            text = "Подобрать направление обучения по предметам ЕГЭ (так же баллы прошлых лет и набор)"
        )
        Text(
            modifier = Modifier.clickable {
                navigator.navigate(
                    direction = WebViewScreenDestination(
                        url = Page.ENTRANCE_EXAMINATIONS.url
                    )
                )
            },
            text = "Перечень вступительных испытаний по направлениям(доп)"
        )
        Text(
            text = "баллы прошлого года(доп)"
        )
        Text(
            modifier = Modifier.clickable {
                navigator.navigate(
                    direction = WebViewScreenDestination(
                        url = Page.RECRUITING_PLAN.url
                    )
                )
            },
            text = "План набора на текущий год(доп)"
        )
        Text(
            modifier = Modifier.clickable {
                navigator.navigate(direction = AchievementsScreenDestination())
            },
            text = "Индивидуальные достижения"
        )
        Spacer(modifier = Modifier.height(5.dp))

        Text(
            modifier = Modifier.clickable {
                navigator.navigate(
                    direction = WebViewScreenDestination(
                        url = Page.EDUCATIONAL_PLANS.url
                    )
                )
            },
            text = "Учебные планы"
        )
        Text(
            modifier = Modifier.clickable {
                navigator.navigate(
                    direction = WebViewScreenDestination(
                        url = Page.GRANTS.url
                    )
                )
            },
            text = "Стипендии"
        )
        Text(
            modifier = Modifier.clickable {
                navigator.navigate(
                    direction = WebViewScreenDestination(
                        url = Page.HOSTEL.url
                    )
                )
            },
            text = "Общежития"
        )
        Spacer(modifier = Modifier.height(5.dp))

        Text(
            modifier = Modifier.clickable {
                navigator.navigate(
                    direction = WebViewScreenDestination(
                        url = Page.CONTRACT.url
                    )
                )
            },
            text = "О контракте"
        )
        Text(
            modifier = Modifier.clickable {
                navigator.navigate(
                    direction = WebViewScreenDestination(
                        url = Page.COST.url
                    )
                )
            },
            text = "Стоимость обучения"
        )
        Text(
            modifier = Modifier.clickable {
                navigator.navigate(
                    direction = WebViewScreenDestination(
                        url = Page.ACCEPTANCE_OF_DOCUMENTS.url
                    )
                )
            },
            text = "Документы"
        )

        Spacer(modifier = Modifier.height(5.dp))
        Text(
            modifier = Modifier.clickable {
                navigator.navigate(direction = RaitingScreenDestination())
            },
            text = "Рейтинговые списки"
        )
    }
}