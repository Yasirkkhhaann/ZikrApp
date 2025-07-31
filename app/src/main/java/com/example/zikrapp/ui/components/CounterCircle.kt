package com.example.zikrapp.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zikrapp.ui.viewmodel.DataBaseViewModel
import kotlinx.serialization.internal.throwMissingFieldException

@Composable
fun MainCounterCircle(incrementCount: () -> Unit,
                      originalSize: Dp = 250.dp,zikrControlModel: DataBaseViewModel = viewModel() ) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val uiState by zikrControlModel.uiState.collectAsState()

    val context = LocalContext.current
    // Animate scale based on press state for the bounce effect
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.97f else 1f, // Shrink when pressed, expand back when released
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "bounceScale"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(originalSize) // Use the fixed original size
            .scale(scale)       // Apply the bounce scale transformation
            .clickable(
                interactionSource = interactionSource,
                indication = null, // Disable default ripple if you want only the bounce
                onClick = {
                    incrementCount();
                if(uiState.isSpeakerOn){
                    if(uiState.countCurrent != uiState.countTotal){
                        SoundPlayer.playclick(context)
                    }

                }// No longer toggling expandContent or changing base size here
                }
            )
            .background(
                color = Color(0xFF262626),
                shape = CircleShape
            )
    ) {}
}
