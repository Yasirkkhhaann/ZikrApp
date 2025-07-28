package com.example.zikrapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zikrapp.R
import com.example.zikrapp.ui.viewmodel.ZikrControlModel


@Composable
fun BottomAppBar(modifier: Modifier = Modifier, onNavigateList: () -> Unit, zikrControlModel: ZikrControlModel = viewModel()) {

            val uiState by zikrControlModel.uiState.collectAsState()

            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CircleIconButton(
                    icon = painterResource(
                        id = if (uiState.isSpeakerOn)
                            R.drawable.speakeron else R.drawable.speakeroff
                    ),
                    onClick = { zikrControlModel.toggleSpeaker() })

                CircleIconButton(
                    icon = painterResource(
                        id = if (uiState.isVibrationOn)
                            R.drawable.vibrationon else R.drawable.vibrationoff
                    ),
                    onClick = { zikrControlModel.toggleVibration() })

                CircleIconButton(
                    icon = painterResource(id = R.drawable.theme2),
                    onClick = { onNavigateList()  })
            }

        }