package com.zxltrxn.nstuproject.common_composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import com.zxltrxn.nstuproject.ui.elevation
import com.zxltrxn.nstuproject.ui.spacing

@Composable
fun LoadingIndicator(zIndex:Float = 2F){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .zIndex(zIndex)
            .padding(vertical = MaterialTheme.spacing.extraSmall),
        contentAlignment = Alignment.TopCenter
    ){
        Surface(
            shape = CircleShape,
            elevation = MaterialTheme.elevation.small
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary
            )
        }
    }
}
