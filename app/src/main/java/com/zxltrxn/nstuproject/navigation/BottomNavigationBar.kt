package com.zxltrxn.nstuproject.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ramcosta.composedestinations.navigation.navigateTo
import com.zxltrxn.nstuproject.features.destinations.Destination
import com.zxltrxn.nstuproject.features.navDestination


@Composable
fun BottomNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val currentDestination:Destination? = navController.currentBackStackEntryAsState()
        .value?.navDestination
    BottomNavigation(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        elevation = 5.dp
    ) {
        BottomBarDestination.values().forEach { item ->
            val isSelected = currentDestination == item.direction
            BottomNavigationItem(
                selected = isSelected,
                onClick = {
                    navController.navigateTo(item.direction) {
                        launchSingleTop = true
                    }
                },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = stringResource(id = item.label)
                        )
                        if(isSelected) {
                            Text(
                                text = stringResource(id = item.label),
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            )
        }
    }

}
