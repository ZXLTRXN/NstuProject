package com.zxltrxn.nstuproject.commonComposable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zxltrxn.nstuproject.ui.spacing

@Composable
fun Header(text: String) {
    Text(text = text, style = MaterialTheme.typography.h1)
    Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
}

@Composable
fun Subtitle(text: String) {
    Text(text = text, style = MaterialTheme.typography.subtitle1)
    Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
}