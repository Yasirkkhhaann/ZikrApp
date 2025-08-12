package com.example.zikrapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zikrapp.R
import com.example.zikrapp.ui.theme1.ui.theme.IconBgTeal
import com.example.zikrapp.ui.viewmodel.DataBaseViewModel


@Composable
fun TopAppBar(modifier: Modifier = Modifier,
              navigateToEditAddScreenToAddNotSavedZikr: () -> Unit,zikrControlModel: DataBaseViewModel = viewModel()) {

    val uiState by zikrControlModel.uiState.collectAsState()
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        CircleIconButton(
            icon = painterResource(id = R.drawable.list3),
            onClick = { zikrControlModel.startThemLogic() })
        Text("Tasbeeh Counter", color = Color.White, fontSize = 24.sp)
            IconButton(
                onClick = { navigateToEditAddScreenToAddNotSavedZikr() },
                enabled = uiState.saveIconEnabled,
                modifier = Modifier
                    .size(50.dp).graphicsLayer{
                        alpha = if(uiState.saveIconEnabled) 1f else 0.3f
                    }
                    .background( IconBgTeal, shape = CircleShape)
            ) {
                Icon(
                    Icons.Default.Save,
                    contentDescription = "Save", tint = Color(0xfff4de94),
                    modifier = Modifier.size(35.dp)
                )
            }
        }
    }


