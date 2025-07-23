package com.example.zikrapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.zikrapp.R
import com.example.zikrapp.ui.viewmodel.ControlsViewModel


@Composable
fun TopAppBar(modifier: Modifier = Modifier,navController: NavController,viewModel: ControlsViewModel = viewModel()) {



        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            CircleIconButton(
                icon = painterResource(id = R.drawable.list3),

                IconColor = Color.White,
                onClick = { viewModel.startThemLogic() })
            Text("Tasbeeh Counter", color = Color.White, fontSize = 16.sp)
            CircleIconButton(
                icon = painterResource(id = R.drawable.save),
                onClick = { })
        }

    }
