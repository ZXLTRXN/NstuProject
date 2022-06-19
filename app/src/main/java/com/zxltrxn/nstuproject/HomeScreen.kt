package com.zxltrxn.nstuproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.simulateHotReload
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.zxltrxn.nstuproject.destinations.ChanceGroupDestination
import com.zxltrxn.nstuproject.destinations.CompetitionGroupDestination
import com.zxltrxn.nstuproject.destinations.DirectionsGroupDestination
import com.zxltrxn.nstuproject.destinations.DocumentsGroupDestination
import com.zxltrxn.nstuproject.destinations.ImportantGroupDestination
import com.zxltrxn.nstuproject.destinations.LeisureGroupDestination
import com.zxltrxn.nstuproject.destinations.PersonalAreaWebViewDestination
import com.zxltrxn.nstuproject.destinations.WebViewScreenDestination
import com.zxltrxn.nstuproject.destinations.PointsScreenDestination
import com.zxltrxn.nstuproject.destinations.PlanScreenDestination
import com.zxltrxn.nstuproject.destinations.PreviousYearPointsScreenDestination
import com.zxltrxn.nstuproject.features.Page
import com.zxltrxn.nstuproject.ui.elevation
import com.zxltrxn.nstuproject.ui.spacing


@Destination(start = true)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    val groups = listOf(
        MenuItem(R.string.directions_group, R.drawable.ic_graduation_cap, DirectionsGroupDestination()),
        MenuItem(R.string.chance_group, R.drawable.ic_medal, ChanceGroupDestination()),
        MenuItem(R.string.documents_group, R.drawable.ic_document, DocumentsGroupDestination()),
        MenuItem(R.string.competition_group, R.drawable.ic_competition, CompetitionGroupDestination()),
        MenuItem(R.string.important_group, R.drawable.ic_student, ImportantGroupDestination()),
        MenuItem(R.string.leisure_group, R.drawable.ic_keytar, LeisureGroupDestination())
    )

    ScreenFrame(headerText = stringResource(id = R.string.home_screen_name)) {
        groups.forEach { item ->
            MenuElement(text = stringResource(id = item.titleId), imageId = item.imageId) {
                navigator.navigate(direction = item.destination)
            }
        }
        MenuElement(text = stringResource(R.string.question), imageId = R.drawable.ic_question) {
            navigator.navigate(direction = WebViewScreenDestination(page = Page.QUESTIONS))
        }
    }
}


@Composable
fun MenuElement(text: String, imageId: Int? = null, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .padding(vertical = MaterialTheme.spacing.small)
            .fillMaxWidth()
            .clickable() { onClick() },
        shape = MaterialTheme.shapes.large,
        elevation = MaterialTheme.elevation.small,
    ) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colors.surface)
                .padding(
                    vertical = MaterialTheme.spacing.medium,
                    horizontal = MaterialTheme.spacing.default
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            imageId?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = "icon"
                )
            }
            Text(
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6,
                text = text
            )
        }
    }
}

@Composable
fun GroupHeader(modifier: Modifier = Modifier, text: String) {
    Box(modifier = modifier) {
        Text(text = text, style = MaterialTheme.typography.h5)
    }
}

@Composable
fun ScreenFrame(
    headerText: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = MaterialTheme.spacing.medium)
    ) {
        GroupHeader(text = headerText)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = content
        )
    }
}

@Destination
@Composable
fun DirectionsGroup(navigator: DestinationsNavigator) {
    ScreenFrame(headerText = stringResource(R.string.directions_group)) {
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

@Destination
@Composable
fun ChanceGroup(navigator: DestinationsNavigator) {
    ScreenFrame(headerText = stringResource(R.string.chance_group)) {
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
            navigator.navigate(
                direction = WebViewScreenDestination(
                    page = Page.INDIVIDUAL_ACHIEVEMENTS
                )
            )
        }
        MenuElement(text = stringResource(R.string.special_rights)) {
            navigator.navigate(
                direction = WebViewScreenDestination(
                    page = Page.SPECIAL_RIGHTS_FOR_WINNERS
                )
            )
        }
    }
}

@Destination
@Composable
fun DocumentsGroup(navigator: DestinationsNavigator) {
    ScreenFrame(headerText = stringResource(R.string.documents_group)) {
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

@Destination
@Composable
fun CompetitionGroup(navigator: DestinationsNavigator) {
    ScreenFrame(headerText = stringResource(R.string.competition_group)) {
        MenuElement(text = stringResource(R.string.common_rating_list)) {
            navigator.navigate(
                direction = WebViewScreenDestination(
                    page = Page.RATING_LIST
                )
            )
        }
        MenuElement(text = stringResource(R.string.personal_area_screen_name)) {
            navigator.navigate(
                direction = PersonalAreaWebViewDestination()
            )
        }
    }
}

@Destination
@Composable
fun ImportantGroup(navigator: DestinationsNavigator) {
    ScreenFrame(headerText = stringResource(R.string.important_group)) {
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

@Destination
@Composable
fun LeisureGroup(navigator: DestinationsNavigator) {
    ScreenFrame(
        headerText = stringResource(R.string.leisure_group)
    ) {
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