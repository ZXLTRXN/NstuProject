package com.zxltrxn.nstuproject.features.parsing.previousYearPoints

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.zxltrxn.nstuproject.commonComposable.SimpleDivider
import com.zxltrxn.nstuproject.commonComposable.Subtitle1
import com.zxltrxn.nstuproject.commonComposable.Subtitle2
import com.zxltrxn.nstuproject.features.parsing.previousYearPoints.PreviousYearPointsViewModel.UiState
import com.zxltrxn.nstuproject.features.parsing.previousYearPoints.model.Direction
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
            )
        }
        is UiState.Loaded -> {
            val state = uiState as UiState.Loaded
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = MaterialTheme.spacing.medium)
            ) {
                Header(text = state.data.title)
                LazyColumn {
                    val titlesModifier = Modifier.fillMaxWidth(0.95f)
                    items(state.data.forms) { form ->
                        val formExpanded = remember { mutableStateOf(false) }
                        ExpandableRow(formExpanded) {
                            Subtitle1(modifier = titlesModifier, text = form.title)
                        }
                        if (formExpanded.value) {
                            for (faculty in form.faculties) {
                                val facultyExpanded = remember { mutableStateOf(false) }
                                ExpandableRow(facultyExpanded) {
                                    Subtitle2(modifier = titlesModifier, text = faculty.name)
                                }
                                if (facultyExpanded.value) {
                                    faculty.directions.map { direction ->
                                        val directionExpanded = remember { mutableStateOf(false) }
                                        DirectionPoints(
                                            direction = direction,
                                            isExpanded = directionExpanded,
                                            onClick = {
                                                directionExpanded.value = !directionExpanded.value
                                            })
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun DirectionPoints(direction: Direction, isExpanded: MutableState<Boolean>, onClick: () -> Unit) {
    Column() {
        with(direction) {
            DirectionContentRow(
                modifier = Modifier
                    .clickable { onClick() }
                    .padding(vertical = MaterialTheme.spacing.extraSmall),
                title = name, value = "",
                titleTextStyle = MaterialTheme.typography.body1,
                valueTextStyle = MaterialTheme.typography.body2
            )

            if (isExpanded.value) {
                budget?.let {
                    DirectionContentRow(
                        title = stringResource(id = R.string.budget_points),
                        value = it
                    )
                }

                contract?.let {
                    SimpleDivider()
                    DirectionContentRow(
                        title = stringResource(id = R.string.contract_points),
                        value = it
                    )
                }
                SimpleDivider()

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            }
        }
    }
}