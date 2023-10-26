package com.example.test.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.test.ui.theme.TestTheme

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun IntrinsicSizeScreenPreview() {
    TestTheme {
        IntrinsicSizeScreen()
    }
}

@Composable
fun IntrinsicSizeScreen() {
    Box {
        val (text, setText) = rememberSaveable { mutableStateOf("") }

        CustomTextField(text = text, onTextChange = setText)

        ConstraintLayout(modifier = Modifier.align(Alignment.Center)) {
            val (col1, col2) = createRefs()
            createVerticalChain(
                col1, col2,
                chainStyle = ChainStyle.Packed
            )

            Column(modifier = Modifier.width(200.dp).padding(5.dp).constrainAs(col1) {
                centerHorizontallyTo(parent)
            }) {
                // 이 Column의 폭을 자식들의 내재적 최소 측정값에 맞춘다
                Column(modifier = Modifier.width(IntrinsicSize.Min)) {
                    Text(text = text, modifier = Modifier.padding(start = 4.dp))
                    Box(
                        modifier = Modifier
                            .height(10.dp)
                            .fillMaxWidth()
                            .background(Color.Blue)
                    )
                    Text(text = "Intrinsic Min Size")
                }
            }

            Column(modifier = Modifier.width(200.dp).padding(5.dp).constrainAs(col2) {
                centerHorizontallyTo(parent)
                top.linkTo(anchor = col1.bottom, margin = 30.dp)
            }) {
                // 이 Column의 폭을 자식들의 내재적 최대 측정값에 맞춘다
                Column(modifier = Modifier.width(IntrinsicSize.Max)) {
                    Text(text = text, modifier = Modifier.padding(start = 4.dp))
                    Box(
                        modifier = Modifier
                            .height(10.dp)
                            .fillMaxWidth()
                            .background(Color.Yellow)
                    )
                    Text(text = "Intrinsic Max Size")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(text: String, onTextChange: (String) -> Unit) {
    TextField(value = text, onValueChange = onTextChange)
}
