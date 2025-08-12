package com.example.zikrapp.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zikrapp.ui.viewmodel.DataBaseViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CountingMain(
    bigTextFontSize: TextUnit,
    zikrControlModel: DataBaseViewModel = viewModel(),
    colorForCountbtn: Color
) {
    val uiState by zikrControlModel.uiState.collectAsState()

    val context = LocalContext.current

    val interactionSource = remember { MutableInteractionSource() }
    Box(
        Modifier.padding(40.dp).fillMaxSize()
            .background(color = colorForCountbtn, shape = CircleShape)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
            ) {


                if (uiState.isSpeakerOn) {
                    if (uiState.countCurrent != uiState.countTotal) {
                        zikrControlModel.playclick(context)
                    }

                }
                if (uiState.isVibrationOn) {
                    if (uiState.countCurrent != uiState.countTotal) {
                        Vibration().vibratee(context, 90)
                    }
                };
                zikrControlModel.incrementCount();
            }) {


        Text(
            text = "Count",
            fontSize = bigTextFontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}