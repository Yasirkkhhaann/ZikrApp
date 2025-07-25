package com.example.zikrapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zikrapp.R
import com.example.zikrapp.data.Zikr
import com.example.zikrapp.ui.components.ActionRow
import com.example.zikrapp.ui.components.BottomAppBar
import com.example.zikrapp.ui.components.MainCounterCircle
import com.example.zikrapp.ui.components.TopAppBar
import com.example.zikrapp.ui.components.ZikrAlertDialog
import com.example.zikrapp.ui.components.ZikrName
import com.example.zikrapp.ui.components.ZikrNote
import com.example.zikrapp.ui.viewmodel.DataBaseViewModel
import com.example.zikrapp.ui.viewmodel.ZikrControlModel


@Composable
fun ZikrCountScreen(
    modifier: Modifier = Modifier,
    onNavigateList: () -> Unit,
    zikrControlModel: ZikrControlModel = viewModel(),
    databaseViewModel: DataBaseViewModel = viewModel(),
    zikrId: Int
    
) {
    val uiState by zikrControlModel.uiState.collectAsState()
    var zikr by remember { mutableStateOf<Zikr?>(null) }

    LaunchedEffect(zikrId) {
        zikr = if (zikrId == 0) null else databaseViewModel.getZikrById(zikrId)
    }

    var zikrName by remember { mutableStateOf("") }
    var zikrStart by remember { mutableStateOf(0) }
    var zikrEnd by remember { mutableStateOf(0) }
    var zikrDescription by remember { mutableStateOf("") }

    LaunchedEffect(zikr) {
        zikrName = zikr?.zikrName ?: ""
        zikrStart = zikr?.zikrCountStart?: 0
        zikrEnd = zikr?.zikrCountEnd?:0
        zikrDescription = zikr?.zikrDescription ?: ""
        zikrControlModel.loadZikrBounds(zikrStart, zikrEnd)
    }
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 40.dp, start = 30.dp, end = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        TopAppBar(modifier = modifier.fillMaxWidth())
        ZikrName(modifier = modifier.fillMaxWidth(),zikrName)
        ZikrNote(
            modifier = modifier
                .fillMaxWidth(),
            zikrDescription
        )

        Spacer(Modifier.height(10.dp))
        ActionRow(
            uiState.start,uiState.end,
            onResetClick = { zikrControlModel.showResetConfirmationDialog() },
            modifier = modifier.fillMaxWidth(),
            zikrControlModel = zikrControlModel
        )
        Spacer(Modifier.height(10.dp))
        MainCounterCircle(incrementCount = zikrControlModel::incrementCount)
        Spacer(Modifier.weight(1f))
        BottomAppBar(onNavigateList = onNavigateList, zikrControlModel = zikrControlModel,
            modifier = modifier.fillMaxWidth().padding(bottom = 30.dp))



        if (uiState.showZikrCompletedDialog) {
            ZikrAlertDialog(
                onDismissRequest = {
                    zikrControlModel.dismissZikrCompletedDialog() // Call the ViewModel function
                },
                onConfirmation = {
                    zikrControlModel.restartZikrAfterCompletion() // This already hides the dialog via the restart logic in ViewModel
                },
                dialogTitle = "Zikr Completed",
                dialogText = "Now you want to restart or Cancel the currentZikr",
                icon = painterResource(id = R.drawable.complete2),

                iconDescription = "Reset Icon",
                color = Color.Green,
                confirmButtonText = "Restart"
            )
        }

        if (uiState.showResetConfirmationDialog) {

            ZikrAlertDialog(
                onDismissRequest = {
                    zikrControlModel.dismissResetConfirmationDialog() // Dismiss if clicked outside or on Cancel
                },
                onConfirmation = {
                    zikrControlModel.confirmAndResetZikr() // Confirm and reset
                },
                dialogTitle = "Reset Zikr!",
                dialogText = "Are you sure you want to reset the current Zikr count?",


                icon = painterResource(id = R.drawable.reset),
                iconDescription = "Reset Icon",
                color = Color.Red,
                confirmButtonText = "Reset"
            )
        }


    }
}



