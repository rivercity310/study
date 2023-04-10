package com.example.slotapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.slotapi.ui.theme.SlotAPITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SlotAPITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   ButtonDemo()
                }
            }
        }
    }
}

@Composable
fun ButtonDemo() {
    Button(onClick = { }) {
        Text("Click Me")
    }
}

@Composable
fun SlotAPI(
    topContent: @Composable () -> Unit,
    middleContent: @Composable () -> Unit,
    bottomContent: @Composable () -> Unit
) {
    Column {
        topContent()
        middleContent()
        bottomContent()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SlotAPITheme {
        SlotAPI(
            topContent = { Text("Top Text") },
            middleContent = { ButtonDemo() },
            bottomContent = { Text("Bottom Text") }
        )
    }
}