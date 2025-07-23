package com.example.zikrapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.zikrapp.R
import com.example.zikrapp.ui.components.ActionRow
import com.example.zikrapp.ui.components.BottomAppBar
import com.example.zikrapp.ui.components.TopAppBar
import com.example.zikrapp.ui.components.MainCounterCircle
import com.example.zikrapp.ui.components.ZikrAlertDialog
import com.example.zikrapp.ui.components.ZikrName
import com.example.zikrapp.ui.components.ZikrNote
import com.example.zikrapp.ui.viewmodel.ControlsViewModel

@Composable
fun ZikrCountScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ControlsViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 40.dp, start = 30.dp, end = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        TopAppBar(navController = navController, modifier = modifier.fillMaxWidth())
        ZikrName(modifier = modifier.fillMaxWidth())
        ZikrNote(
            modifier = modifier
                .fillMaxWidth(),
        )

        Spacer(Modifier.height(10.dp))
        ActionRow(
            navController = navController, uiState.countCurrent,
            uiState.countTotal,
            onResetClick = { viewModel.showResetConfirmationDialog() },
            modifier = modifier.fillMaxWidth(),
            viewModel = viewModel
        )
        Spacer(Modifier.height(10.dp))
        MainCounterCircle(incrementCount = viewModel::incrementCount)
        Spacer(Modifier.weight(1f))
        BottomAppBar(navController = navController, viewModel = viewModel,
            modifier = modifier.fillMaxWidth().padding(bottom = 30.dp))



        if (uiState.showZikrCompletedDialog) {
            ZikrAlertDialog(
                onDismissRequest = {
                    viewModel.dismissZikrCompletedDialog() // Call the ViewModel function
                },
                onConfirmation = {
                    viewModel.restartZikrAfterCompletion() // This already hides the dialog via the restart logic in ViewModel
                },
                dialogTitle = "Zikr Completed",
                dialogText = "Now you want to restart or Cancel the Zikr?",
                icon = painterResource(id = R.drawable.complete2),

                iconDescription = "Reset Icon",
                color = Color.Green,
                confirmButtonText = "Restart"
            )
        }

        if (uiState.showResetConfirmationDialog) {

            ZikrAlertDialog(
                onDismissRequest = {
                    viewModel.dismissResetConfirmationDialog() // Dismiss if clicked outside or on Cancel
                },
                onConfirmation = {
                    viewModel.confirmAndResetZikr() // Confirm and reset
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



