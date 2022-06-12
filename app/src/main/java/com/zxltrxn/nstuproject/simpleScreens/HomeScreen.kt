package com.zxltrxn.nstuproject.simpleScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MenuElement(text = stringResource(R.string.min_points)) {
            navigator.navigate(direction = PointsScreenDestination())
        }
        MenuElement(text = stringResource(R.string.directions_selection)) {
            navigator.navigate(
                direction = WebViewScreenDestination(
                    page = Page.SEARCH_BACHELORS_PROGRAMS
                )
            )
        }
        MenuElement(text = stringResource(R.string.entrance_exams)) {
            navigator.navigate(
                direction = WebViewScreenDestination(
                    page = Page.ENTRANCE_EXAMINATIONS
                )
            )
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
        Spacer(modifier = Modifier.height(5.dp))
        MenuElement(text = stringResource(R.string.study_plans)) {
            navigator.navigate(
                direction = WebViewScreenDestination(
                    page = Page.EDUCATIONAL_PLANS
                )
            )
        }
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
        Spacer(modifier = Modifier.height(5.dp))

        MenuElement(text = stringResource(R.string.contract_execution)) {
            navigator.navigate(
                direction = WebViewScreenDestination(
                    page = Page.CONTRACT
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
        MenuElement(text = stringResource(R.string.documents)) {
            navigator.navigate(
                direction = WebViewScreenDestination(
                    page = Page.ACCEPTANCE_OF_DOCUMENTS
                )
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        MenuElement(text = stringResource(R.string.rating_list)) {
            navigator.navigate(direction = RatingScreenDestination())
        }
        MenuElement(text = stringResource(R.string.polyclinic)) {
            navigator.navigate(
                direction = WebViewScreenDestination(
                    page = Page.POLYCLINIC
                )
            )
        }
        MenuElement(text = stringResource(R.string.cultural_center)) {
            navigator.navigate(
                direction = WebViewScreenDestination(
                    page = Page.CULTURAL
                )
            )
        }
        MenuElement(text = stringResource(R.string.question)) {
            navigator.navigate(
                direction = WebViewScreenDestination(
                    page = Page.QUESTIONS
                )
            )
        }
    }
}

@Composable
fun MenuElement(text: String, onClick:()->Unit){
    Text(
        modifier = Modifier.clickable() { onClick() },
        style = MaterialTheme.typography.h6,
        text = text
    )
}