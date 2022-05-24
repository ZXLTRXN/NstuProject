package com.zxltrxn.nstuproject.commonComposable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zxltrxn.nstuproject.R
import com.zxltrxn.nstuproject.ui.spacing

@Composable
fun CustomDivider(index: Int, lastIndex: Int) {
    if (index == lastIndex)
        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.extraSmall)
        )
    else
        SimpleDivider()
}

@Composable
fun SimpleDivider() =
    Divider(
        modifier = Modifier
            .height(4.dp)
            .padding(top = 3.dp)
    )

@Composable
fun ExpandableRow(isExpanded: MutableState<Boolean>, content: @Composable RowScope.() -> Unit) {
    val modifier = Modifier
        .fillMaxWidth()
        .clickable {
            isExpanded.value = !isExpanded.value
        }
        .padding(vertical = MaterialTheme.spacing.medium)

    Row(modifier = modifier, verticalAlignment = Alignment.Bottom) {
        content()
        Image(
            painter = painterResource(
                id = if (isExpanded.value) R.drawable.ic_arrow_down else R.drawable.ic_arrow_right
            ),
            contentDescription = "open spoiler"
        )
    }
}