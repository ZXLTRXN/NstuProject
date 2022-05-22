package com.zxltrxn.nstuproject.features.parsing.plan.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.zxltrxn.nstuproject.commonComposable.CustomDivider
import com.zxltrxn.nstuproject.commonComposable.ErrorMessage
import com.zxltrxn.nstuproject.commonComposable.Header
import com.zxltrxn.nstuproject.commonComposable.LoadingIndicator
import com.zxltrxn.nstuproject.commonComposable.Subtitle1
import com.zxltrxn.nstuproject.commonComposable.Subtitle2
import com.zxltrxn.nstuproject.features.parsing.plan.domain.model.Direction
import com.zxltrxn.nstuproject.features.parsing.plan.presentation.PlanViewModel.UiState
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
        is UiState.Loaded -> {
            val state = uiState as UiState.Loaded
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = MaterialTheme.spacing.medium)
            ) {
                Header(text = state.data.title)
                LazyColumn {
                    items(state.data.forms){ form ->
                        Subtitle1(text = form.title)
                        for(faculty in form.faculties){
                            Subtitle2(text = faculty.name)
                            val last = faculty.directions.lastIndex
                            faculty.directions.mapIndexed{ i, direction ->
                                Direction(
                                    direction = direction
                                )
                                CustomDivider(index = i, lastIndex = last)
                            }
                        }
                        Divider(modifier = Modifier.height(1.dp))
                    }

                }
            }


        }
        is UiState.Error -> {
            ErrorMessage(
                message = (uiState as UiState.Error).message.getString(
                    context = LocalContext.current
                )
            )
        }
    }
}

@Composable
fun Direction(direction: Direction){
    Row {
        Text(text = direction.code,
            style = MaterialTheme.typography.body2)
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
        Text(text = direction.name)
    }
    direction.budget?.let {
        Row() {
            Text(text = "бюджет")
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
            Text(text = it)
        }
    }




}