package com.example.zikrapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.zikrapp.R
import com.example.zikrapp.ui.screens.ZikrScreen
import com.example.zikrapp.ui.viewmodel.ControlsViewModel


@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun FloatingBottomBar(navController: NavController) {
    val navBackStackEntry = remember(navController) {
        navController.getBackStackEntry("main")
    }
    val viewModel: ControlsViewModel = viewModel(navBackStackEntry)
    Box(
        Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp), // Padding from the bottom
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .height(64.dp),
            shape = RoundedCornerShape(25.dp),
            color = Color(0xFF363636), // Light purple background
            shadowElevation = 15.dp // Shadow/elevation effect
        ) {
            val uiState by viewModel.uiState.collectAsState()

            Row(
                modifier = Modifier.padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                CircleIconButton(
                    icon = painterResource(
                        id = if (uiState.isSpeakerOn)
                            R.drawable.speakeron else R.drawable.speakeroff
                    ),
                    onClick = { viewModel.toggleSpeaker() })

                CircleIconButton(
                    icon = painterResource(
                        id = if (uiState.isVibrationOn)
                            R.drawable.vibrationon else R.drawable.vibrationoff
                    ),
                    onClick = { viewModel.toggleVibration() })

                CircleIconButton(
                    icon = painterResource(id = R.drawable.theme2),
                    onClick = { navController.navigate(ZikrScreen.ZikrList.name)  })
            }

        }

    }
}


@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun FloatingTopBar(navController: NavController) {


    val navBackStackEntry = remember(navController) {
        navController.getBackStackEntry("main")
    }
    val viewModel: ControlsViewModel = viewModel(navBackStackEntry)
    Box(
        Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp,  bottom = 8.dp, top = 16.dp),
    ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                CircleIconButton(
                    icon = painterResource(id = R.drawable.list3),
                    Circlesize = 35.dp,
                    Iconsize = 18.dp,

                    IconColor = Color.White,
                    onClick = { viewModel.startThemLogic() })
                Text("Tasbeeh Counter", color = Color.White, fontSize = 16.sp)
                CircleIconButton(
                    icon = painterResource(id = R.drawable.save),
                    Circlesize = 35.dp,
                    Iconsize = 18.dp,
                    onClick = { })
            }

        }

    }



