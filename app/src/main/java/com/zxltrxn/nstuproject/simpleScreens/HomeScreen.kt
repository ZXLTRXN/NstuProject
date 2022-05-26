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
import com.zxltrxn.nstuproject.destinations.AchievementsScreenDestination
import com.zxltrxn.nstuproject.destinations.PointsScreenDestination
import com.zxltrxn.nstuproject.destinations.PlanScreenDestination
import com.zxltrxn.nstuproject.destinations.PreviousYearPointsScreenDestination
import com.zxltrxn.nstuproject.destinations.RatingScreenDestination
import com.zxltrxn.nstuproject.features.Page
import com.zxltrxn.nstuproject.features.parsing.previousYearPoints.PreviousYearPointsScreen


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
                        page = Page.SEARCH_BACHELORS_PROGRAMS
                    )
                )
            },
            text = "Подобрать направление обучения по предметам ЕГЭ (так же баллы прошлых лет и набор)"
        )
        Text(
            modifier = Modifier.clickable {
                navigator.navigate(
                    direction = WebViewScreenDestination(
                        page = Page.ENTRANCE_EXAMINATIONS
                    )
                )
            },
            text = "Перечень вступительных испытаний по направлениям(доп)"
        )
        Text(
            modifier = Modifier.clickable {
                navigator.navigate(direction = PreviousYearPointsScreenDestination())
            },
            text = "баллы прошлого года(доп)"
        )
        Text(
            modifier = Modifier.clickable {
                navigator.navigate(direction = PlanScreenDestination())
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
                        page = Page.EDUCATIONAL_PLANS
                    )
                )
            },
            text = "Учебные планы"
        )
        Text(
            modifier = Modifier.clickable {
                navigator.navigate(
                    direction = WebViewScreenDestination(
                        page = Page.GRANTS
                    )
                )
            },
            text = "Стипендии"
        )
        Text(
            modifier = Modifier.clickable {
                navigator.navigate(
                    direction = WebViewScreenDestination(
                        page = Page.HOSTEL
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
                        page = Page.CONTRACT
                    )
                )
            },
            text = "О контракте"
        )
        Text(
            modifier = Modifier.clickable {
                navigator.navigate(
                    direction = WebViewScreenDestination(
                        page = Page.COST
                    )
                )
            },
            text = "Стоимость обучения"
        )
        Text(
            modifier = Modifier.clickable {
                navigator.navigate(
                    direction = WebViewScreenDestination(
                        page = Page.ACCEPTANCE_OF_DOCUMENTS
                    )
                )
            },
            text = "Документы"
        )

        Spacer(modifier = Modifier.height(5.dp))
        Text(
            modifier = Modifier.clickable {
                navigator.navigate(direction = RatingScreenDestination())
            },
            text = "Рейтинговые списки"
        )
    }
}