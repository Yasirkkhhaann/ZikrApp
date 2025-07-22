package com.example.zikrapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zikrapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZikrAppBar(modofier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                "Tasbeeh Dhikr", style = TextStyle(
                    fontSize = 16.sp, color = Color.White,
                )
            )
        },
        actions = {
            CircleIconButton(icon = painterResource(id = R.drawable.save), onClick = {})
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0XFF1A1A1A),
        ),
        // match dark theme
    )
}


