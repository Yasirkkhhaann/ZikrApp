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
fun ZikrName(modifier: Modifier = Modifier) {
    Text("Allah o Akbar", modifier = modifier ,color = Color.White,
        fontSize = 16.sp,
        textAlign = TextAlign.Center)
}

@Composable
fun ZikrNote(modifier: Modifier = Modifier) {
    Text("Allah o Akbar Allah o Akbar Allah o Akbar Allah o Akbar Allah o Akbar Allah o Akbar",
        modifier = modifier,color = Color.White, fontSize = 12.sp,
        lineHeight = 15.sp,
        fontStyle = FontStyle.Italic, fontWeight = FontWeight.Thin,
        textAlign = TextAlign.Center)
}

@Composable
fun ZikrCountStatus(countCurrent: Int,countTotal: Int) {

    Text("$countCurrent/$countTotal", color = Color.White, fontSize = 16.sp)
}


