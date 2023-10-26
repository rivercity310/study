package com.example.test.ui.theme.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.test.ui.theme.TestTheme

@Preview(showBackground = true)
@Composable
fun ModifierScreenPreview() {
    TestTheme {
        ModifierScreen()
    }
}

@Composable
fun ModifierScreen() {
    val modifier1 = Modifier
        .padding(all = 10.dp)
        .border(width = 2.dp, color = Color.Red)

    val modifier2 = Modifier
        .border(width = 2.dp, color = Color.Yellow)
        .padding(all = 10.dp)

    Column {
        Text(text = "Modifier 1", modifier = modifier1)
        Text(text = "Modifier 2", modifier = modifier2)
    }
}