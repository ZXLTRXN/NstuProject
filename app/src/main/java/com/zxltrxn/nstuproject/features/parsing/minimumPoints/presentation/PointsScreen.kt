package com.zxltrxn.nstuproject.features.parsing.minimumPoints.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.zxltrxn.nstuproject.commonComposable.ErrorMessage
import com.zxltrxn.nstuproject.commonComposable.Header
import com.zxltrxn.nstuproject.commonComposable.LoadingIndicator
import com.zxltrxn.nstuproject.commonComposable.Subtitle
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.domain.model.SubjectWithPoints
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.presentation.PointsVM.UiState
import com.zxltrxn.nstuproject.ui.spacing

@Destination
@Composable
fun PointsScreen(
    modifier: Modifier = Modifier,
) {
    val vm = hiltViewModel<PointsVM>()
    val uiState by vm.uiState

    when (uiState) {
        is UiState.IsLoading -> {
            LoadingIndicator()
        }
        is UiState.Loaded -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = MaterialTheme.spacing.medium)
            ) {
                Header(text = (uiState as UiState.Loaded).data.title)

                LazyColumn() {
                    items((uiState as UiState.Loaded).data.tables) { table ->
                        Subtitle(text = table.title)
                        for (subject in table.items) {
                            SubjectRow(subject)
                            Spacer(
                                modifier = Modifier
                                    .height(MaterialTheme.spacing.extraSmall)
                            )
                        }
                    }
                }
            }
        }
        is UiState.Error -> {

            ErrorMessage(message = (uiState as UiState.Error).message.getString(context = LocalContext.current))
        }
    }
}

@Composable
fun SubjectRow(item: SubjectWithPoints) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(0.85f),
            text = item.subjectName,
        )
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = item.points.toString()
        )
    }
}