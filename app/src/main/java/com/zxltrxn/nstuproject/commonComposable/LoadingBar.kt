package com.zxltrxn.nstuproject.commonComposable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import com.zxltrxn.nstuproject.ui.spacing

@Composable
fun LoadingIndicator(percentage: Int = 0, zIndex: Float = 2F) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .zIndex(zIndex)
            .padding(vertical = MaterialTheme.spacing.extraSmall),
        contentAlignment = Alignment.TopCenter
    ) {
//        val progressValue: Float = percentage.toFloat() / 100
//        Surface(
//            shape = CircleShape,
//            elevation = MaterialTheme.elevation.small
//        ) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.primary,
        )
//        }
    }
}
