package com.zxltrxn.nstuproject.simpleScreens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.zxltrxn.nstuproject.R
import com.zxltrxn.nstuproject.destinations.WebViewScreenDestination
import com.zxltrxn.nstuproject.destinations.AchievementsScreenDestination
import com.zxltrxn.nstuproject.destinations.PointsScreenDestination
import com.zxltrxn.nstuproject.destinations.PlanScreenDestination
import com.zxltrxn.nstuproject.destinations.PreviousYearPointsScreenDestination
import com.zxltrxn.nstuproject.destinations.RatingScreenDestination
import com.zxltrxn.nstuproject.features.Page


@Destination(start = true)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    var groupSelected by remember { mutableStateOf<Int?>(null) }

    val groups = listOf(
        R.string.directions_group,
        R.string.chance_group,
        R.string.documents_group,
        R.string.competition_group,
        R.string.important_group,
        R.string.leisure_group
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (groupSelected == null) {
            groups.forEachIndexed { idx, nameId ->
                MenuElement(stringResource(id = nameId)) {
                    groupSelected = idx
                }
            }
            MenuElement(text = stringResource(R.string.question)) {
                navigator.navigate(WebViewScreenDestination(page = Page.QUESTIONS))
            }
        } else {
            when (groupSelected){
                0 -> DirectionsGroup(navigator)
                1 -> ChanceGroup(navigator)
                2 -> DocumentsGroup(navigator)
                3 -> CompetitionGroup(navigator)
                4 -> ImportantGroup(navigator)
                5 -> LeisureGroup(navigator)
                else -> Unit
            }
        }
    }
    BackHandler(groupSelected != null) {
        groupSelected = null
    }
}

@Composable
fun MenuElement(text: String, onClick: () -> Unit) {
    Text(
        modifier = Modifier.clickable() { onClick() },
        style = MaterialTheme.typography.h6,
        text = text
    )
}

@Composable
fun GroupHeader(modifier: Modifier = Modifier, text: String) {
    Text(
        style = MaterialTheme.typography.h5,
        text = text
    )
}

@Composable
fun DirectionsGroup(navigator: DestinationsNavigator) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        GroupHeader(text = stringResource(R.string.directions_group))

        MenuElement(text = stringResource(R.string.directions_selection)) {
            navigator.navigate(
                direction = WebViewScreenDestination(
                    page = Page.SEARCH_BACHELORS_PROGRAMS
                )
            )
        }
        MenuElement(text = stringResource(R.string.entrance_exams)) { // либо
            navigator.navigate(
                direction = WebViewScreenDestination(
                    page = Page.ENTRANCE_EXAMINATIONS
                )
            )
        }
        MenuElement(text = stringResource(R.string.study_plans)) {
            navigator.navigate(
                direction = WebViewScreenDestination(
                    page = Page.EDUCATIONAL_PLANS
                )
            )
        }
    }
}

@Composable
fun ChanceGroup(navigator: DestinationsNavigator) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        GroupHeader(text = stringResource(R.string.chance_group))

        MenuElement(text = stringResource(R.string.min_points)) {
            navigator.navigate(direction = PointsScreenDestination())
        }
        MenuElement(text = stringResource(R.string.previous_year_points)) {
            navigator.navigate(direction = PreviousYearPointsScreenDestination())
        }
        MenuElement(text = stringResource(R.string.plan)) {
            navigator.navigate(direction = PlanScreenDestination())
        }
        MenuElement(text = stringResource(R.string.individual_achievements)) {
            navigator.navigate(direction = AchievementsScreenDestination())
        }
    }
}

@Composable
fun DocumentsGroup(navigator: DestinationsNavigator) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        GroupHeader(text = stringResource(R.string.documents_group))

        MenuElement(text = stringResource(R.string.documents)) {
            navigator.navigate(
                direction = WebViewScreenDestination(
                    page = Page.ACCEPTANCE_OF_DOCUMENTS
                )
            )
        }
        MenuElement(text = stringResource(R.string.cost)) {
            navigator.navigate(
                direction = WebViewScreenDestination(
                    page = Page.COST
                )
            )
        }
        MenuElement(text = stringResource(R.string.contract_execution)) {
            navigator.navigate(
                direction = WebViewScreenDestination(
                    page = Page.CONTRACT
                )
            )
        }
    }
}

@Composable
fun CompetitionGroup(navigator: DestinationsNavigator) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        GroupHeader(text = stringResource(R.string.competition_group))
        MenuElement(text = stringResource(R.string.rating_list)) {
            navigator.navigate(direction = RatingScreenDestination())
        }
    }
}

@Composable
fun ImportantGroup(navigator: DestinationsNavigator) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        GroupHeader(text = stringResource(R.string.important_group))

        MenuElement(text = stringResource(R.string.grants)) {
            navigator.navigate(
                direction = WebViewScreenDestination(
                    page = Page.GRANTS
                )
            )
        }
        MenuElement(text = stringResource(R.string.hostel)) {
            navigator.navigate(
                direction = WebViewScreenDestination(
                    page = Page.HOSTEL
                )
            )
        }
        MenuElement(text = stringResource(R.string.polyclinic)) {
            navigator.navigate(
                direction = WebViewScreenDestination(
                    page = Page.POLYCLINIC
                )
            )
        }
        MenuElement(text = stringResource(R.string.campus_card)) {
            navigator.navigate(
                direction = WebViewScreenDestination(
                    page = Page.CAMPUS_CARD
                )
            )
        }
    }
}

@Composable
fun LeisureGroup(navigator: DestinationsNavigator) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        GroupHeader(text = stringResource(R.string.leisure_group))

        MenuElement(text = stringResource(R.string.cultural_center)) {
            navigator.navigate(
                direction = WebViewScreenDestination(
                    page = Page.CULTURAL
                )
            )
        }
        MenuElement(text = stringResource(R.string.sport)) {
            navigator.navigate(
                direction = WebViewScreenDestination(
                    page = Page.SPORTS
                )
            )
        }
    }
}