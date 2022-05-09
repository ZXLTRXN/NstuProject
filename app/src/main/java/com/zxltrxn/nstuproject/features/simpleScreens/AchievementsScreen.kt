package com.zxltrxn.nstuproject.features.simpleScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AchievementsScreen(
    navigator: DestinationsNavigator,
    modifier:Modifier = Modifier
){
    Column() {
        Text("достижения")
    }
}