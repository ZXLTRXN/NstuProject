package com.zxltrxn.nstuproject.commonComposable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.zxltrxn.nstuproject.R

@Composable
fun ErrorMessage(message: String, onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(modifier = Modifier.align(Alignment.Center), text = message)
        Button(modifier = Modifier.align(Alignment.BottomCenter),
            onClick = onClick) {
            Text(text = stringResource(id = R.string.retry_button))
        }
    }
}