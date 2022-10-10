package com.danilkinkin.buckwheat.finishPeriod

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danilkinkin.buckwheat.ui.BuckwheatTheme

@Composable
fun FillCircleStub() {
    var size by remember { mutableStateOf(0.dp) }
    val localDensity = LocalDensity.current

    Box(
        Modifier
            .fillMaxHeight()
            .onGloballyPositioned {
                size = with(localDensity) { it.size.height.toDp() }
            }
    ) {
        Card(
            modifier = Modifier.size(size),
            shape = CircleShape,
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "\uD83C\uDF89",
                    fontSize = with(localDensity) { (size * 0.4f).toPx().toSp() }
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    BuckwheatTheme {
        Box(
            modifier = Modifier
                .height(200.dp)
                .width(900.dp)
        ) {
            FillCircleStub()
        }
    }
}