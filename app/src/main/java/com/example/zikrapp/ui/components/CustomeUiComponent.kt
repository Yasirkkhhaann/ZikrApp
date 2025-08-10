//package com.example.zikrapp.ui.components
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.requiredSize
//import androidx.compose.foundation.layout.size
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.drawBehind
//import androidx.compose.ui.geometry.*
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.StrokeCap
//import androidx.compose.ui.graphics.drawscope.DrawScope
//import androidx.compose.ui.graphics.drawscope.Stroke
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import com.example.zikrapp.ui.theme.ZikrAppTheme
//
//
//@Composable
//fun CustomeUiComponent(
//    canvaSize: Dp = 300.dp,
//    indicatorValue: Int = 0,
//    maxIndicatorValue: Int = 100,
//    backgroundIndicatorColor: Color,
//    backgroundIndicatorStrokeWidth: Float,
//
//    ) {
//    Column(
//        modifier = Modifier
//            .size(canvaSize)
//            .drawBehind {
//                val componentSize = size / 1.25f
//                backgroundIndicator(
//                    componentSize = componentSize,
//                    indicatorColor = Color,
//                    indicatorStrokeWidth = 5f
//                )
//            }) {
//
//    }
//}
//
//fun DrawScope.backgroundIndicator(
//    componentSize: Size,indicatorColor: Color, indicatorStrokeWidth: Float
//) {
//
//
//    drawArc(
//        color = indicatorColor,
//        startAngle = 150f,
//        sweepAngle = 240f,
//        useCenter = false,
//        style = Stroke(
//            width = indicatorStrokeWidth, cap = StrokeCap.Round
//        )
//    )
//}
//
//@Preview
//@Composable
//fun CustomeUiComponentPreview() {
//    CustomeUiComponent( )
//}