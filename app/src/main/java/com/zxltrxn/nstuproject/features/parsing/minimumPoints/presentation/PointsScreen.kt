package com.zxltrxn.nstuproject.features.parsing.minimumPoints.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.zxltrxn.nstuproject.commonComposable.CustomDivider
import com.zxltrxn.nstuproject.commonComposable.ErrorMessage
import com.zxltrxn.nstuproject.commonComposable.ExpandableRow
import com.zxltrxn.nstuproject.commonComposable.Header
import com.zxltrxn.nstuproject.commonComposable.LoadingIndicator
import com.zxltrxn.nstuproject.commonComposable.Subtitle1
import com.zxltrxn.nstuproject.features.parsing.commonPresentation.UiState
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.domain.model.Subject

import com.zxltrxn.nstuproject.ui.spacing

@Destination
@Composable
fun PointsScreen(
    modifier: Modifier = Modifier,
) {
    val vm = hiltViewModel<PointsViewModel>()
    val uiState by vm.uiState

    when (uiState) {
        is UiState.IsLoading -> LoadingIndicator()
        is UiState.Error -> {
            ErrorMessage(message = (uiState as UiState.Error).message.getString(context = LocalContext.current))
        }
        is UiState.Loaded -> {
            val state = uiState as UiState.Loaded
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = MaterialTheme.spacing.medium)
            ) {
                Header(text = state.data.title)

                val titlesModifier = Modifier.fillMaxWidth(0.95f)
                LazyColumn() {
                    items(state.data.bases) { base ->
                        val baseExpanded = remember { mutableStateOf(false) }
                        ExpandableRow(baseExpanded) {
                            Subtitle1(modifier = titlesModifier, text = base.title)
                        }
                        if (baseExpanded.value) {
                            val last = base.items.lastIndex
                            base.items.mapIndexed { i, subject ->
                                SubjectRow(subject)
                                CustomDivider(index = i, lastIndex = last)
                            }
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun SubjectRow(item: Subject) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(0.85f),
            text = item.name,
        )
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = item.points.toString()
        )
    }
}