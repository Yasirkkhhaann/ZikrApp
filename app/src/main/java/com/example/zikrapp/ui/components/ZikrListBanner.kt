package com.example.zikrapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zikrapp.R
import com.example.zikrapp.ui.theme1.ui.theme.TealBottom
import com.example.zikrapp.ui.theme1.ui.theme.TealTop

@Composable
fun ZikrListBanner() {

    ElevatedCard(
        shape = RoundedCornerShape(bottomStart = 75.dp, bottomEnd = 75.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.inversePrimary),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .size(200.dp)
    ) {
        val colors = arrayOf(
            0.1f to TealTop,
            0.9f to TealBottom
        )

        val brush = Brush.linearGradient(colorStops = colors)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush
                )
        ) {


            Column {
                Text(
                    "Zikr Gallery", style = TextStyle(
                        color = Color.Black,
                        fontSize = 32.sp, letterSpacing = 2.sp
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                )

                Row {
                    Text(
                        "This is your list of Zikr collection here you can add," +
                                " edit, delete and update you Zikrs", style = TextStyle(
                            color = Color.Black,
                            fontSize = 14.sp,
                        ),
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 10.dp, start = 60.dp, end = 10.dp)
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.gallery),
                        contentDescription = "gallery",
                        tint = Color.Black,
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxHeight()
                            .padding(end = 10.dp)
                    )
                }

            }
        }
    }

}

@Preview
@Composable
fun ZikrListBannerPreview() {
    ZikrListBanner()
}