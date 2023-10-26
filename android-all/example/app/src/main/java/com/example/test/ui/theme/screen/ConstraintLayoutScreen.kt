package com.example.test.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import com.example.test.ui.theme.TestTheme

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ConstraintLayoutScreenPreview() {
    TestTheme {
        Box {
            ConstraintLayoutScreen(modifier = Modifier.align(Alignment.TopCenter))
            ConstraintLayoutBarrierScreen(modifier = Modifier.align(Alignment.Center))
            ConstraintSetScreen(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
fun ConstraintLayoutScreen(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier
            .size(width = 400.dp, height = 220.dp)
            .background(color = Color.Gray)
    ) {
        val (btn1, btn2, btn3) = createRefs()

        /*
        MyButton(text = "Button1", modifier = Modifier.constrainAs(btn1) {
            // top.linkTo(anchor = parent.top, margin = 60.dp)

            // 반대 제약 형태 1
            // start.linkTo(anchor = parent.start)
            // end.linkTo(anchor = parent.end)

            // 반대 제약 형태 2
            // linkTo(parent.start, parent.end)

            // 반대 제약 형태 3
            centerHorizontallyTo(parent)
            centerVerticallyTo(parent)
        })

        MyButton(text = "Button2", modifier = Modifier.constrainAs(btn2) {
            linkTo(parent.start, parent.end, bias = 0.3f)
            top.linkTo(anchor = btn1.bottom)
        })
        */

        /*
        createHorizontalChain(
            btn1, btn2, btn3,
            chainStyle = ChainStyle.SpreadInside
        )
        */

        val guide = createGuidelineFromStart(fraction = 0.6f)

        MyButton(text = "btn1", modifier = Modifier.constrainAs(btn1) {
            top.linkTo(anchor = parent.top, margin = 30.dp)
            end.linkTo(anchor = guide, margin = 30.dp)
        })

        MyButton(text = "btn2", modifier = Modifier.constrainAs(btn2) {
            top.linkTo(anchor = btn1.bottom, margin = 20.dp)
            start.linkTo(anchor = guide, margin = 40.dp)
        })

        MyButton(text = "btn3", modifier = Modifier.constrainAs(btn3) {
            top.linkTo(anchor = btn2.bottom, margin = 40.dp)
            end.linkTo(anchor = guide, margin = 20.dp)
        })
    }
}

@Composable
fun ConstraintLayoutBarrierScreen(modifier: Modifier = Modifier) {
    ConstraintLayout(modifier = modifier.size(width = 350.dp, height = 220.dp)) {
        val (btn1, btn2, btn3) = createRefs()

        val barrier = createEndBarrier(btn1, btn2)

        MyButton(text = "btn1", modifier = Modifier
            .width(100.dp)
            .constrainAs(btn1) {
                top.linkTo(anchor = parent.top, margin = 30.dp)
                start.linkTo(anchor = parent.start, margin = 8.dp)
            })

        MyButton(text = "btn2", modifier = Modifier
            .width(150.dp)
            .constrainAs(btn2) {
                top.linkTo(anchor = btn1.bottom, margin = 20.dp)
                start.linkTo(anchor = parent.start, margin = 8.dp)
            })

        MyButton(text = "btn3", modifier = Modifier.constrainAs(btn3) {
            linkTo(top = parent.top, bottom = parent.bottom, topMargin = 8.dp, bottomMargin = 8.dp)
            linkTo(start = btn1.end, end = parent.end, startMargin = 30.dp, endMargin = 8.dp)

            // 배리어 적용
            start.linkTo(anchor = barrier, margin = 30.dp)

            // 제약이 허용하는 최대 공간 채우도록 설정
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        })
    }
}

@Composable
fun ConstraintSetScreen(modifier: Modifier = Modifier) {
    val constraints = myConstraintSet(16.dp)

    ConstraintLayout(constraintSet = constraints, modifier = modifier.size(width = 200.dp, height = 200.dp)) {
        MyButton(text = "Btn5", modifier = Modifier
            .size(200.dp)
            .layoutId("button1"))
    }
}

@Composable
fun MyButton(text: String, modifier: Modifier = Modifier) {
    Button(onClick = { /*TODO*/ }, modifier = modifier) {
        Text(text = text)
    }
}

private fun myConstraintSet(margin: Dp) : ConstraintSet {
    return ConstraintSet {
        val button1 = createRefFor("button1")

        constrain(button1) {
            linkTo(top = parent.top, bottom = parent.bottom, topMargin = margin, bottomMargin = margin)
            linkTo(start = parent.start, end = parent.end, startMargin = margin, endMargin = margin)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }
    }
}