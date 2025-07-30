package com.example.zikrapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zikrapp.R
import com.example.zikrapp.ui.viewmodel.DataBaseViewModel


@Composable
fun BottomAppBar(modifier: Modifier = Modifier,printLastZikrId: () -> Unit,increaseid: () -> Unit, onNavigateList: () -> Unit, zikrControlModel: DataBaseViewModel = viewModel()) {

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
                    onClick = { zikrControlModel.toggleSpeaker();printLastZikrId() })

                CircleIconButton(
                    icon = painterResource(
                        id = if (uiState.isVibrationOn)
                            R.drawable.vibrationon else R.drawable.vibrationoff
                    ),
                    onClick = { zikrControlModel.toggleVibration();increaseid() })

                CircleIconButton(
                    icon = painterResource(id = R.drawable.theme2),
                    onClick = { onNavigateList()  })
            }

        }