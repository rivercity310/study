package com.example.test.ui.theme.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.test.ui.theme.TestTheme

val LocalColor = staticCompositionLocalOf { Color(0xFFffdbcf) }

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CompositionLocalScreenPreview() {
    TestTheme {
        CompositionLocalScreen()
    }
}

@Composable
fun CompositionLocalScreen() {
    val color =
        if (isSystemInDarkTheme()) Color(0xFFa08d87)
        else Color(0xFFffdbcf)

    Column {
        Composable2()

        CompositionLocalProvider(LocalColor provides color) {
            Composable3()
        }
    }
}

@Composable
fun Composable2() {
    Composable4()
}

@Composable
fun Composable3() {
    Text(text = "Composable 3", modifier = Modifier.background(LocalColor.current))

    CompositionLocalProvider(LocalColor provides Color.Red) {
        Composable5()
    }
}

@Composable
fun Composable4() {
    Composable6()
}

@Composable
fun Composable5() {
    Text(text = "Composable 5", modifier = Modifier.background(LocalColor.current))

    CompositionLocalProvider(LocalColor provides Color.Green) {
        Composable7()
    }

    CompositionLocalProvider(LocalColor provides Color.Yellow) {
        Composable8()
    }
}

@Composable
fun Composable6() {
    Text(text = "Composable 6", modifier = Modifier.background(LocalColor.current))
}

@Composable
fun Composable7() {
    Text(text = "Composable 7", modifier = Modifier.background(LocalColor.current))
}

@Composable
fun Composable8() {
    Text(text = "Composable 8", modifier = Modifier.background(LocalColor.current))
}