package com.zxltrxn.nstuproject.commonComposable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.zxltrxn.nstuproject.R
import com.zxltrxn.nstuproject.features.parsing.commonPresentation.UiState
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


fun MutableState<Boolean>.toggle(){
    this.value = !this.value
}

@Composable
fun ExpandableRow(
    isExpanded: State<Boolean>,
    toggle: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    val modifier = Modifier
        .fillMaxWidth()
        .clickable {
            toggle()
        }
        .padding(vertical = MaterialTheme.spacing.medium)

    Row(modifier = modifier, verticalAlignment = Alignment.Bottom) {
        content()
        Image(
            painter = painterResource(
                id = if (isExpanded.value)
                    R.drawable.ic_arrow_down
                else R.drawable.ic_arrow_right
            ),
            contentDescription = "open spoiler"
        )
    }
}

@Composable
fun DirectionContentRow(
    modifier: Modifier = Modifier,
    title: String, value: String,
    titleTextStyle: TextStyle = MaterialTheme.typography.body2,
    valueTextStyle: TextStyle = MaterialTheme.typography.body1,
    titleMaxWidth: Float = 0.65f
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(titleMaxWidth),
            text = title,
            style = titleTextStyle
        )
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = value,
            style = valueTextStyle
        )
    }
}