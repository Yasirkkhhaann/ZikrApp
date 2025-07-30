package com.example.zikrapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zikrapp.R

@Composable
fun ZikrListBanner() {

    ElevatedCard(
        shape = RoundedCornerShape(bottomStart = 75.dp, bottomEnd = 75.dp),
        colors = CardDefaults.cardColors(Color(0XFF262626)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .size(230.dp)
    ) {


        Text(
            "Zikr Gallery", style = TextStyle(
                color = Color.White,
                fontSize = 32.sp, letterSpacing = 2.sp
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )

        Row{
            Text(
                "This is your list of Zikr collection here you can add," +
                        " edit, delete and update you Zikrs", style = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp, fontWeight = FontWeight.Thin
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 30.dp, start = 60.dp, end = 10.dp )
            )

            Icon(
                painter = painterResource(id = R.drawable.gallery),
                contentDescription = "gallery", tint = Color( 0XFF01F6A4),
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight()
                    .padding(top = 30.dp, end = 30.dp)
            )
        }


    }

}

@Preview
@Composable
fun ZikrListBannerPreview() {
    ZikrListBanner()
}