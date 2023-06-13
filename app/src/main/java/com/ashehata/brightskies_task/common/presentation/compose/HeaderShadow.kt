package com.ashehata.brightskies_task.common.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.constraintlayout.compose.Dimension

@Composable
fun HeaderShadow(
    dividerColor: Color = Color.Gray,
    content: @Composable ConstraintLayoutScope.(Modifier) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        val (header, line) = createRefs()

        content(Modifier.constrainAs(header) {
            linkTo(parent.start, parent.end)
            linkTo(parent.top, parent.bottom)
        })


        Divider(modifier = Modifier
            .background(dividerColor)
            .height(.5.dp)
            .constrainAs(line) {
                linkTo(parent.top, parent.bottom, bias = 1F)
                width = Dimension.fillToConstraints
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
    }
}