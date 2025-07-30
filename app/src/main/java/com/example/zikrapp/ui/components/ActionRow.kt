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
import com.example.zikrapp.R
import com.example.zikrapp.ui.viewmodel.DataBaseViewModel


@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun ActionRow(
    currentCount: Int, // Add this
    totalCount: Int,
    onResetClick: () -> Unit,
    modifier: Modifier = Modifier,
    zikrControlModel: DataBaseViewModel = viewModel()
) {

    val uiState by zikrControlModel.uiState.collectAsState()


    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        CircleIconButton(
            icon = painterResource(
                id = if (uiState.islock)
                    R.drawable.lock else R.drawable.unlock,


                ), onClick = { zikrControlModel.toggleLock() })
        ZikrCountStatus(currentCount, totalCount)
        CircleIconButton(
            icon = painterResource(id = R.drawable.reset),
            onClick = {

                onResetClick()
            })

    }

}


