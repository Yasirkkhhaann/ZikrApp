package com.example.zikrapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zikrapp.R
import com.example.zikrapp.ui.viewmodel.DataBaseViewModel


@Composable
fun TopAppBar(modifier: Modifier = Modifier,zikrControlModel: DataBaseViewModel = viewModel()) {



        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            CircleIconButton(
                icon = painterResource(id = R.drawable.list3),

                IconColor = Color.White,
                onClick = { zikrControlModel.startThemLogic() })
            Text("Tasbeeh Counter", color = Color.White, fontSize = 16.sp)
            CircleIconButton(
                icon = painterResource(id = R.drawable.save),
                onClick = { })
        }

    }
