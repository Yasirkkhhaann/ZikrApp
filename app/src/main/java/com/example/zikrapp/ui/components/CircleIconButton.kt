package com.example.zikrapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircleIconButton(icon: Painter, Circlesize: Dp = 65.dp,Iconsize: Dp = 31.dp, onClick: () -> Unit, IconColor: Color = Color.White, bgColor: Color = Color(0xFF363636)) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(Circlesize)
            .background(bgColor, shape = CircleShape).padding(5.dp)
    ) {
        Icon(icon, contentDescription = null,tint = IconColor, modifier = Modifier.size(Iconsize))
    }
}

