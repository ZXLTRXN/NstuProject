package com.zxltrxn.nstuproject.features.parsing.plan.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.zxltrxn.nstuproject.R
import com.zxltrxn.nstuproject.commonComposable.ColoredBox
import com.zxltrxn.nstuproject.commonComposable.DirectionContentRow
import com.zxltrxn.nstuproject.commonComposable.ErrorMessage
import com.zxltrxn.nstuproject.commonComposable.ExpandableRow
import com.zxltrxn.nstuproject.commonComposable.Header
import com.zxltrxn.nstuproject.commonComposable.LoadingIndicator
import com.zxltrxn.nstuproject.commonComposable.RowWithIcon
import com.zxltrxn.nstuproject.commonComposable.SimpleDivider
import com.zxltrxn.nstuproject.commonComposable.Subtitle1
import com.zxltrxn.nstuproject.commonComposable.Subtitle2
import com.zxltrxn.nstuproject.commonComposable.toggle
import com.zxltrxn.nstuproject.features.parsing.commonPresentation.UiState
import com.zxltrxn.nstuproject.features.parsing.plan.domain.model.Direction
import com.zxltrxn.nstuproject.features.parsing.plan.domain.model.Faculty
import com.zxltrxn.nstuproject.features.parsing.plan.domain.model.Form
import com.zxltrxn.nstuproject.ui.spacing

@Destination
@Composable
fun PlanScreen(
    modifier: Modifier = Modifier,
) {
    val vm = hiltViewModel<PlanViewModel>()
    val uiState by vm.uiState

    when (uiState) {
        is UiState.IsLoading -> LoadingIndicator()
        is UiState.Error -> {
            ErrorMessage(
                message = (uiState as UiState.Error).message.getString(
                    context = LocalContext.current
                )
            ){
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
                val chosenFormIndex = remember { mutableStateOf<Int?>(null) }

                if (chosenFaculty.value == null) {
                    Header(text = state.data.title)
                    FormsExpandable(state.data.forms, chosenFormIndex.value) { formIndex, facultyIndex ->
                        chosenFormIndex.value = formIndex
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
fun FormsExpandable(forms: List<Form>, openedFormIndex: Int?,onClick: (Int, Int) -> Unit) {
    val formExpanded = remember(forms) { forms.map { mutableStateOf(false) }.toMutableStateList() }
    openedFormIndex?.let {
        formExpanded[it].value = true
    }
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
                    ColoredBox(isColored = i % 2 == 0) {
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
                    }
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
                ColoredBox(isColored = idx % 2 == 0) {
                    Direction(direction = direction, isExpanded = isExpanded) {
                        directionExpanded[idx].toggle()
                    }
                }
            }
        }
    }
}


@Composable
fun Direction(direction: Direction, isExpanded: State<Boolean>, onClick: () -> Unit) {
    Column(modifier = Modifier
        .clickable { onClick() }) {
        with(direction) {
            DirectionContentRow(
                modifier = Modifier
                    .padding(vertical = MaterialTheme.spacing.extraSmall),
                title = name, value = code,
                titleTextStyle = MaterialTheme.typography.subtitle2,
                valueTextStyle = MaterialTheme.typography.body2
            )

            if (isExpanded.value) {
                special?.let {
                    DirectionContentRow(
                        title = stringResource(R.string.special), value = it,
                        titleTextStyle = MaterialTheme.typography.body2
                    )
                    SimpleDivider()
                }

                val elements: List<Pair<Int, String?>> = listOf(
                    Pair(R.string.budget_count, budget),
                    Pair(R.string.contract_count, contract),
                    Pair(R.string.quota_count, quota),
                    Pair(R.string.target_count, target),
                    Pair(R.string.competitive_group, competitiveGroup),
                    Pair(R.string.accreditation_period, accreditationPeriod),
                )
                val last = elements.lastIndex
                elements.forEachIndexed { index, pair ->
                    val (id, value) = pair
                    value?.let {
                        DirectionContentRow(
                            title = stringResource(id = id),
                            value = it
                        )
                        if (index != last) SimpleDivider()
                    }
                }
            }
        }
    }
}