package com.zxltrxn.nstuproject.commonComposable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zxltrxn.nstuproject.ui.spacing

@Composable
fun CustomDivider(index: Int, lastIndex: Int) {
    if (index == lastIndex)
        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.extraSmall)
        )
    else
        Divider(modifier = Modifier.height(4.dp).padding(top = 3.dp))
}