package com.zxltrxn.nstuproject.commonComposable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.zxltrxn.nstuproject.R
import com.zxltrxn.nstuproject.features.parsing.commonPresentation.UiState
import com.zxltrxn.nstuproject.features.parsing.previousYearPoints.DirectionPoints
import com.zxltrxn.nstuproject.ui.spacing


@Composable
fun SimpleDivider() =
    Divider(
        modifier = Modifier
            .height(1.dp)
    )


fun MutableState<Boolean>.toggle() {
    this.value = !this.value
}

@Composable
fun RowWithIcon(
    modifier: Modifier,
    resourceId: Int,
    contentDescription: String,
    content: @Composable BoxScope.() -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = Modifier.fillMaxWidth(0.95f)){
            content()
        }
        Image(
            painter = painterResource(
                id = resourceId
            ),
            contentDescription = contentDescription
        )
    }
}

@Composable
fun ColoredBox(isColored: Boolean, content: @Composable BoxScope.() -> Unit){
    var modifier = Modifier
        .clip(shape = MaterialTheme.shapes.medium)
    if (isColored) modifier = modifier.background(MaterialTheme.colors.surface)

    Box(modifier = modifier.padding(horizontal = MaterialTheme.spacing.default)){
        content()
    }
}

@Composable
fun ExpandableRow(
    isExpanded: State<Boolean>,
    toggle: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    val modifier = Modifier
        .clickable {
            toggle()
        }
        .padding(vertical = MaterialTheme.spacing.medium)

    val iconId = if (isExpanded.value) R.drawable.ic_arrow_down else R.drawable.ic_arrow_right
    RowWithIcon(
        modifier = modifier,
        resourceId = iconId,
        content = content,
        contentDescription = "open spoiler"
    )
}

@Composable
fun DirectionContentRow(
    modifier: Modifier = Modifier,
    title: String, value: String,
    titleTextStyle: TextStyle = MaterialTheme.typography.body1,
    valueTextStyle: TextStyle = MaterialTheme.typography.body2,
    titleMaxWidth: Float = 0.65f
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(vertical = MaterialTheme.spacing.extraSmall),
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