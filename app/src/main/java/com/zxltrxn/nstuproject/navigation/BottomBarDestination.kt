package com.zxltrxn.nstuproject.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.ui.graphics.vector.ImageVector
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.zxltrxn.nstuproject.R
import com.zxltrxn.nstuproject.features.destinations.*

enum class BottomBarDestination(
    val direction:DirectionDestinationSpec,
    val icon: ImageVector,
    @StringRes val label:Int
){
    Home(HomeScreenDestination, Icons.Default.Home, R.string.home_screen_name),
    Phone(ContactsWebViewDestination, Icons.Default.Phone, R.string.contacts_screen_name),
    PersonalArea(PersonalAreaWebViewDestination, Icons.Default.Person, R.string.personal_area_screen_name)
}























