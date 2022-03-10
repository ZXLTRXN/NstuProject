package com.zxltrxn.nstuproject.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val default:Dp = 0.dp,
    val extraSmall: Dp = 6.dp, //верт отступы от подзаголовка
    val small: Dp = 10.dp, // верт отступы от заголовка
    val medium: Dp = 16.dp, // горизонт отступы от краев
    val large: Dp = 32.dp,
    val extraLarge: Dp = 64.dp,
)

val LocalSpacing = compositionLocalOf { Spacing() }

val MaterialTheme.spacing:Spacing
@Composable
@ReadOnlyComposable
get() = LocalSpacing.current