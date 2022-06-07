package com.zxltrxn.nstuproject.features.parsing.previousYearPoints


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.zxltrxn.nstuproject.R
import com.zxltrxn.nstuproject.commonComposable.DirectionContentRow
import com.zxltrxn.nstuproject.commonComposable.ErrorMessage
import com.zxltrxn.nstuproject.commonComposable.ExpandableRow
import com.zxltrxn.nstuproject.commonComposable.Header
import com.zxltrxn.nstuproject.commonComposable.LoadingIndicator
import com.zxltrxn.nstuproject.commonComposable.RowWithIcon
import com.zxltrxn.nstuproject.commonComposable.SimpleDivider
import com.zxltrxn.nstuproject.commonComposable.Subtitle1
import com.zxltrxn.nstuproject.commonComposable.toggle
import com.zxltrxn.nstuproject.features.parsing.commonPresentation.UiState
import com.zxltrxn.nstuproject.features.parsing.previousYearPoints.model.Direction
import com.zxltrxn.nstuproject.features.parsing.previousYearPoints.model.Faculty
import com.zxltrxn.nstuproject.features.parsing.previousYearPoints.model.Form
import com.zxltrxn.nstuproject.ui.spacing

@Destination
@Composable
fun PreviousYearPointsScreen(
    modifier: Modifier = Modifier,
) {
    val vm = hiltViewModel<PreviousYearPointsViewModel>()
    val uiState by vm.uiState

    when (uiState) {
        is UiState.IsLoading -> LoadingIndicator()
        is UiState.Error -> {
            ErrorMessage(
                message = (uiState as UiState.Error).message.getString(
                    context = LocalContext.current
                )
            ) {
                vm.retry()
            }
        }
        is UiState.Loaded -> {
            val state = uiState as UiState.Loaded
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = MaterialTheme.spacing.medium)
            ) {
                val chosenFaculty = remember { mutableStateOf<Faculty?>(null) }

                if (chosenFaculty.value == null) {
                    Header(text = state.data.title)
                    FormsExpandable(state.data.forms) { formIndex, facultyIndex ->
                        chosenFaculty.value = state.data.forms[formIndex].faculties[facultyIndex]
                    }
                }

                chosenFaculty.value?.let {
                    Header(text = it.name)
                    DirectionsExpandable(directions = it.directions)
                }
                BackHandler(chosenFaculty.value != null) {
                    chosenFaculty.value = null
                }
            }
        }
    }
}

@Composable
fun FormsExpandable(forms: List<Form>, onClick: (Int, Int) -> Unit) {
    val formExpanded = remember(forms) { forms.map { mutableStateOf(false) }.toMutableStateList() }
    LazyColumn {
        forms.forEachIndexed { idx, form ->
            val isExpanded = formExpanded[idx]
            item(key = "form $idx") {
                ExpandableRow(isExpanded = isExpanded, toggle = { formExpanded[idx].toggle() }) {
                    Subtitle1(text = form.title)
                }
            }
            if (isExpanded.value) {
                itemsIndexed(form.faculties) { i, faculty ->
                    RowWithIcon(
                        modifier = Modifier
                            .clickable {
                                onClick(idx, i)
                            }
                            .padding(vertical = MaterialTheme.spacing.small),
                        resourceId = R.drawable.ic_arrow_right,
                        contentDescription = "to directions"
                    ) {
                        Text(
                            text = faculty.name
                        )
                    }
                    SimpleDivider()
                }
            }
        }
    }
}

@Composable
fun DirectionsExpandable(directions: List<Direction>) {
    val directionExpanded =
        remember(directions) { directions.map { mutableStateOf(false) }.toMutableStateList() }
    LazyColumn {
        directions.forEachIndexed { idx, direction ->
            val isExpanded = directionExpanded[idx]
            item(key = "direction $idx") {
                DirectionPoints(direction = direction, isExpanded = isExpanded) {
                    directionExpanded[idx].toggle()
                }
            }
        }
    }
}

@Composable
fun DirectionPoints(direction: Direction, isExpanded: State<Boolean>, onClick: () -> Unit) {
    Column(modifier = Modifier
        .clickable { onClick() }
        .padding(vertical = MaterialTheme.spacing.extraSmall)) {
        with(direction) {
            DirectionContentRow(
                title = name, value = "",
                titleTextStyle = MaterialTheme.typography.subtitle2,
                valueTextStyle = MaterialTheme.typography.body2,
                titleMaxWidth = 1f
            )

            if (isExpanded.value) {
                DirectionContentRow(
                    title = stringResource(id = R.string.profile),
                    value = profile,
                    titleMaxWidth = 0.3f
                )

                budget?.let {
                    DirectionContentRow(
                        title = stringResource(id = R.string.budget_points),
                        value = it
                    )
                }

                contract?.let {
                    DirectionContentRow(
                        title = stringResource(id = R.string.contract_points),
                        value = it
                    )
                }
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            }
            SimpleDivider()
        }
    }
}