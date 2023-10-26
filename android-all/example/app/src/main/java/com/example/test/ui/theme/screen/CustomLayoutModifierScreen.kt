package com.example.test.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.test.ui.theme.TestTheme
import kotlin.math.roundToInt

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CustomLayoutModifierScreenPreview() {
    TestTheme {
        CustomLayoutScreen()
    }
}

@Composable
fun CustomLayoutModifierScreen() {
    Box(
        modifier = Modifier.size(120.dp, 80.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            ColorBox(modifier = Modifier
                .exampleLayout2(0f)
                .background(Color.Blue))
            ColorBox(modifier = Modifier
                .exampleLayout2(0.25f)
                .background(Color.Green))
            ColorBox(modifier = Modifier
                .exampleLayout2(0.5f)
                .background(Color.Yellow))
            ColorBox(modifier = Modifier
                .exampleLayout2(0.25f)
                .background(Color.Red))
        }
    }
}

@Composable
fun ColorBox(modifier: Modifier) {
    val colorBoxModifier = Modifier
        .padding(1.dp)
        .size(width = 50.dp, height = 10.dp)
        .then(modifier)

    Box(modifier = colorBoxModifier)
}

fun Modifier.exampleLayout(x: Int, y: Int) = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    layout(placeable.width, placeable.height) {
        placeable.placeRelative(x, y)
    }
}

fun Modifier.exampleLayout2(fraction: Float) = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    val x = -(placeable.width * fraction).roundToInt()

    layout(placeable.width, placeable.height) {
        placeable.placeRelative(x = x, y = 0)
    }
}
