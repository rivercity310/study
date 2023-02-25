package com.example.slotapi2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.slotapi2.ui.theme.SlotAPI2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SlotAPI2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var linearSelected by rememberSaveable { mutableStateOf(true) }
    var imageSelected by rememberSaveable { mutableStateOf(true) }

    val onLinearClick = { value: Boolean -> linearSelected = value }
    val onTitleClick = { value: Boolean -> imageSelected = value }

    @Composable
    fun titleContent() {
        if (imageSelected) {
            TitleImage(drawing = R.drawable.baseline_cloud_download_24)
        } else {
            Text("Downloading", style = MaterialTheme.typography.h3, modifier = Modifier.padding(30.dp))
        }
    }

    @Composable
    fun progressContent() {
        if (linearSelected) {
            LinearProgressIndicator(Modifier.height(40.dp))
        } else {
            CircularProgressIndicator(Modifier.size(200.dp), strokeWidth = 18.dp)
        }
    }

    ScreenContent(
        linearSelected = linearSelected,
        imageSelected = imageSelected,
        onTitleClick = onTitleClick,
        onLinearClick = onLinearClick,
        titleContent = { titleContent() },
        progressContent = { progressContent() }
    )
}

@Composable
fun ScreenContent(
    linearSelected: Boolean,
    imageSelected: Boolean,
    onTitleClick: (Boolean) -> Unit,
    onLinearClick: (Boolean) -> Unit,
    titleContent: @Composable () -> Unit,
    progressContent: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        titleContent()
        progressContent()
        CheckBoxes(linearSelected, imageSelected, onTitleClick, onLinearClick)
    }
}

@Composable
fun CheckBoxes(
    linearSelected: Boolean,
    imageSelected: Boolean,
    onTitleClick: (Boolean) -> Unit,
    onLinearClick: (Boolean) -> Unit
) {
    Row(
        Modifier.padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            checked = imageSelected,
            onCheckedChange = onTitleClick
        )

        Text("Image Title")

        Spacer(Modifier.width(20.dp))

        Checkbox(
            checked = linearSelected,
            onCheckedChange = onLinearClick
        )

        Text("Linear Progress")
    }
}

@Composable
fun TitleImage(drawing: Int) {
    Image(painter = painterResource(drawing), contentDescription = "title image")
}

@Preview(showSystemUi = true)
@Composable
fun DemoPreview() {
    MainScreen()
}