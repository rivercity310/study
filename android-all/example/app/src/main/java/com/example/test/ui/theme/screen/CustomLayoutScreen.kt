package com.example.test.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.test.ui.theme.TestTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomLayoutPreview() {
    TestTheme {
        CustomLayoutScreen()
    }
}

@Composable
fun CustomLayoutScreen() {
    Box {
        CascadeLayout() {
            Box(modifier = Modifier.size(60.dp).background(Color.Blue))
            Box(modifier = Modifier.size(80.dp).background(Color.Yellow))
            Box(modifier = Modifier.size(90.dp).background(Color.Green))
            Box(modifier = Modifier.size(50.dp).background(Color.Black))
            Box(modifier = Modifier.size(70.dp).background(Color.Cyan))
        }
    }
}

@Composable
fun CustomBoxLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placeables: List<Placeable> = measurables.map { measurable ->
            measurable.measure(constraints)
        }

        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEach { placeable ->
                placeable.placeRelative(x = 0, y = 0)
            }
        }
    }
}

@Composable
fun CascadeLayout(
    modifier: Modifier = Modifier,
    spacing: Int = 0,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placeables: List<Placeable> = measurables.map { measurable ->
            measurable.measure(constraints)
        }

        var indent = 0
        var ycoord = 0

        layout(width = constraints.maxWidth, height = constraints.maxHeight) {
            placeables.forEach { placeable ->
                placeable.placeRelative(x = indent, y = ycoord)
                indent += placeable.width + spacing
                ycoord += placeable.height + spacing
            }
        }
    }
}