package com.example.zikrapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.zikrapp.R
import com.example.zikrapp.ui.viewmodel.ControlsViewModel



@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun ActionRow(navController: NavController,
              currentCount: Int, // Add this
              totalCount: Int,
              onResetClick: () -> Unit) {




    val navBackStackEntry = remember(navController) {
        navController.getBackStackEntry("main")
    }
    val viewModel: ControlsViewModel = viewModel(navBackStackEntry)
    val uiState by viewModel.uiState.collectAsState()

    Box(
        Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp,top = 16.dp),
    ) {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .height(64.dp),
            shape = RoundedCornerShape(0.dp),
            color = Color(0XFF1A1A1A), // Light purple background
            shadowElevation = 0.dp // Shadow/elevation effect
        ) {


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceAround
            ) {

                CircleIconButton(
                    Circlesize = 50.dp,
                    Iconsize = 25.dp,
                    icon = painterResource(
                        id = if (uiState.islock)
                            R.drawable.lock else R.drawable.unlock,


                        ), onClick = { viewModel.toggleLock() })
ZikrCountStatus(currentCount,totalCount)
               CircleIconButton(
                    Circlesize = 50.dp,
                    Iconsize = 25.dp,
                    icon = painterResource(id = R.drawable.reset),
                    onClick = {

                        onResetClick()
                    })

            }

        }

    }
}


