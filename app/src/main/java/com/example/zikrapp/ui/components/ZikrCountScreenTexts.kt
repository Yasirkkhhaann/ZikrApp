package com.example.zikrapp.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


@Composable
fun ZikrName(modifier: Modifier = Modifier,zikrName: String) {
    Text(zikrName, modifier = modifier ,color = Color.White,
        fontSize = 16.sp,
        textAlign = TextAlign.Center)
}

@Composable
fun ZikrNote(modifier: Modifier = Modifier,zikrDescription: String) {
    Text(text =  zikrDescription,
    modifier = modifier,color = Color.White, fontSize = 12.sp,
        lineHeight = 15.sp,
        fontStyle = FontStyle.Italic, fontWeight = FontWeight.Thin,
        textAlign = TextAlign.Center)
}

@Composable
fun ZikrCountStatus(zikrStart: Int,zikrEnd: Int) {

    Text("${zikrStart}/${zikrEnd}", color = Color.White, fontSize = 16.sp)
}


