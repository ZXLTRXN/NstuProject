package com.zxltrxn.nstuproject.features.parsing.minimum_points.presentation

import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.zxltrxn.nstuproject.common_composable.LoadingIndicator
import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.model.PointsTable
import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.model.SubjectWithPoints
import com.zxltrxn.nstuproject.ui.spacing
import dagger.hilt.EntryPoint


@Destination(start = true)
@Composable
fun PointsScreen(
    modifier: Modifier = Modifier,
){
    val vm = hiltViewModel<PointsVM>()
    val uiState by vm.uiState
    if(uiState.isLoading) {
        LoadingIndicator()
    }
    else{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = MaterialTheme.spacing.medium)
        ) {
            Text(text = uiState.data.title, style = MaterialTheme.typography.h1)

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

            for (table in uiState.data.tables){
                Text(text = table.title, style = MaterialTheme.typography.h2)

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

                LazyColumn(){
                    items(table.items) { item ->
                        SubjectRow(item)
                    }
                }
            }
        }
    }
    when(uiState.error){
        UiState.Error.NetworkError -> {
            Text(text = "Сеть не доступна", )
        }
        else -> {}
    }
}

@Composable
fun SubjectRow(item:SubjectWithPoints){
    Column(){
        Text(item.subjectName)
        Text(item.points.toString())
    }
}