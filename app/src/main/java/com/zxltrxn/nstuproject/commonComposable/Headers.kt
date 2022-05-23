package com.zxltrxn.nstuproject.commonComposable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zxltrxn.nstuproject.ui.spacing

@Composable
fun Header(modifier: Modifier = Modifier, text: String) {
    Box(modifier = modifier) {
        Text(text = text, style = MaterialTheme.typography.h1)
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
    }
}

@Composable
fun Subtitle1(modifier: Modifier = Modifier, text: String) {
    Box(modifier = modifier) {
        Text(text = text, style = MaterialTheme.typography.subtitle1)
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
    }

}

@Composable
fun Subtitle2(modifier: Modifier = Modifier, text: String) {
    Box(modifier = modifier) {
        Text(text = text, style = MaterialTheme.typography.subtitle2)
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
    }
}