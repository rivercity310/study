package com.example.test.ui.theme.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.ui.theme.TestTheme

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LayoutScreenPreview() {
    TestTheme {
        LayoutScreen()
    }
}

@Composable
fun LayoutScreen() {
    // RowScreen()
    // ColumnScreen()
    // RowAlignBy()
    BoxScreen()
}

@Composable
fun RowScreen() {
    Row(modifier = Modifier.height(300.dp)) {
        TextCell(text = "1", Modifier.align(Alignment.Top))
        TextCell(text = "2", Modifier.align(Alignment.CenterVertically))
        TextCell(text = "3", Modifier.align(Alignment.Bottom))
    }
}

@Composable
fun RowAlignBy() {
    Row {
        Text(
            text = "Large",
            modifier = Modifier.alignByBaseline(),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = "small",
            modifier = Modifier.alignByBaseline(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun ColumnScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TextCell(text = "1")
        TextCell(text = "2")
        TextCell(text = "3")
    }
}

@Composable
fun RowColumnScreen() {
    Column {
        Row {
            Column {
                TextCell(text = "1")
                TextCell(text = "2")
                TextCell(text = "3")
            }

            Column {
                TextCell(text = "4")
                TextCell(text = "5")
                TextCell(text = "6")
            }

            Column {
                TextCell(text = "7")
                TextCell(text = "8")
            }
        }

        Row {
            TextCell(text = "9")
            TextCell(text = "10")
            TextCell(text = "11")
        }
    }
}

@Composable
fun BoxScreen() {
    Box(
        contentAlignment = Alignment.CenterEnd,
        modifier = Modifier.size(400.dp, 400.dp)
    ) {
        val width = 200.dp
        val height = 200.dp

        TextCell2(text = "1", modifier = Modifier.size(width = width, height = height))
        TextCell2(text = "2", modifier = Modifier.size(width = width, height = height))
        TextCell2(text = "3", modifier = Modifier.size(width = width, height = height))
    }
}

@Composable
private fun TextCell(text: String, modifier: Modifier = Modifier) {
    val cellModifier = Modifier
        .padding(4.dp)
        .size(100.dp, 100.dp)
        .border(width = 4.dp, color = Color.Gray)

    Text(
        text = text,
        modifier = cellModifier.then(modifier),
        fontSize = 70.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun TextCell2(text: String, modifier: Modifier = Modifier, fontSize: Int = 100) {
    val cellModifier = Modifier
        .padding(4.dp)
        .border(width = 5.dp, color = Color.Black)

    Surface {
        Text(
            text = text,
            modifier = cellModifier.then(modifier),
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}