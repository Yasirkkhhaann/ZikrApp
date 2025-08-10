package com.example.zikrapp.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.zikrapp.ui.theme1.ui.theme.PrimaryTextWhite
import com.example.zikrapp.ui.theme1.ui.theme.SecondaryTextMint


@Composable
fun ZikrName(modifier: Modifier = Modifier,zikrName: String) {
    Text(zikrName, modifier = modifier,
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        color = PrimaryTextWhite)
}

@Composable
fun ZikrNote(modifier: Modifier = Modifier,zikrDescription: String) {
    Text(text =  zikrDescription,
    modifier = modifier, fontSize = 18.sp,
        lineHeight = 15.sp,
        fontStyle = FontStyle.Italic,
        textAlign = TextAlign.Center,
        color = SecondaryTextMint)
}

@Composable
fun ZikrCountStatus(zikrStart: Int,zikrEnd: Int) {

    Text(" ${zikrStart} / ${if(zikrEnd==0) " ∞ " else zikrEnd}",fontSize = 32.sp,color = Color.White)
}


