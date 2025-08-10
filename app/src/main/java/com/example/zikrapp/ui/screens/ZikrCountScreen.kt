package com.example.zikrapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zikrapp.R
import com.example.zikrapp.data.Zikr
import com.example.zikrapp.ui.components.ActionRow
import com.example.zikrapp.ui.components.BottomAppBar
import com.example.zikrapp.ui.components.CustomComponent
import com.example.zikrapp.ui.components.TopAppBar
import com.example.zikrapp.ui.components.ZikrAlertDialog
import com.example.zikrapp.ui.components.ZikrName
import com.example.zikrapp.ui.components.ZikrNote
import com.example.zikrapp.ui.theme1.ui.theme.DeepTeal
import com.example.zikrapp.ui.theme1.ui.theme.DeepTealforBg
import com.example.zikrapp.ui.theme1.ui.theme.IconBgTeal
import com.example.zikrapp.ui.theme1.ui.theme.MintGreen
import com.example.zikrapp.ui.theme1.ui.theme.MintGreen2
import com.example.zikrapp.ui.theme1.ui.theme.MintGreenforBg
import com.example.zikrapp.ui.theme1.ui.theme.TealBottom
import com.example.zikrapp.ui.theme1.ui.theme.TealTop
import com.example.zikrapp.ui.viewmodel.DataBaseViewModel
import kotlinx.coroutines.flow.Flow

@SuppressLint(
    "UnrememberedMutableState", "CoroutineCreationDuringComposition",
    "SuspiciousIndentation"
)
@Composable
fun ZikrCountScreen(
    modifier: Modifier = Modifier,
    onNavigateList: () -> Unit,
    zikrControlModel: DataBaseViewModel = viewModel(),

    ) {
    val uiState by zikrControlModel.uiState.collectAsState()


    var zikrFlow by remember { mutableStateOf<Flow<Zikr?>?>(null) }
    LaunchedEffect(uiState.lastZikrId) {
        zikrFlow = zikrControlModel.getZikrById(uiState.lastZikrId)
    }


    val zikr by zikrFlow?.collectAsState(initial = null) ?: mutableStateOf(null)

    val context = LocalContext.current
    var zikrName by remember { mutableStateOf("") }
    var zikrStart by remember { mutableIntStateOf(0) }
    var zikrEnd by remember { mutableIntStateOf(0) }
    var zikrDescription by remember { mutableStateOf("") }

    LaunchedEffect(zikr) {
        zikrName = zikr?.zikrName ?: ""
        zikrStart = zikr?.zikrCountStart ?: 0
        zikrEnd = zikr?.zikrCountEnd ?: 0
        zikrDescription = zikr?.zikrDescription ?: ""


            zikrControlModel.loadZikrBounds(zikrStart, zikrEnd)



    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(TealTop, TealBottom)
                )
            )
    ) {

        Column(
            modifier = modifier
                .padding(top = 80.dp, start = 30.dp, end = 30.dp),
        ) {

            TopAppBar(modifier = modifier.fillMaxWidth())
            ZikrName(modifier = modifier.fillMaxWidth(), zikrName)
            ZikrNote(
                modifier = modifier
                    .fillMaxWidth().height(50.dp),
                zikrDescription
            )

            Spacer(Modifier.height(20.dp))
            ActionRow(
                uiState.countCurrent,
                uiState.countTotal,
                onResetClick = { zikrControlModel.showResetConfirmationDialog() },
                modifier = modifier.fillMaxWidth(),
                zikrControlModel = zikrControlModel
            )
            Spacer(Modifier.height(100.dp))

            val colors = arrayOf(
                0.1f to MintGreenforBg,
                0.9f to DeepTealforBg,
            )
            val brush = Brush.linearGradient(colorStops = colors)


            val colors2 = arrayOf(

                0.6f to MintGreen2,
                0.9f to MaterialTheme.colorScheme.errorContainer,
            )
            val brush2 = Brush.linearGradient(colorStops = colors2)

            val progressBrush = Brush.sweepGradient(
                listOf(MintGreen, DeepTeal)
            )


//            LaunchedEffect(uiState.countCurrent) {
//                if (uiState.lastZikrId == 0) {
//                    while (true) {
//                        delay(10000)
//                        Toast
//                            .makeText(context, "No Zikr Found. Kindly select one from the list.", Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                }
//            }

                        if (uiState.lastZikrId != 0) {
                // Data is ready
                CustomComponent(
                    indicatorValue = uiState.countCurrent,
                    maxIndicatorValue = if(uiState.countTotal==0) 1 else uiState.countTotal,
                    foregroundIndicatorColor = brush2,
                    backgroundIndicatorColor = brush,
                    colorForCountbtn = IconBgTeal
                )
            } else{
                // Load

                            CustomComponent(
                                indicatorValue = uiState.countCurrent,
                                maxIndicatorValue = if(uiState.countTotal==0) 1000 else uiState.countTotal,
                                foregroundIndicatorColor = brush2,
                                backgroundIndicatorColor = brush,
                                colorForCountbtn = IconBgTeal
                            )


            }



//            LaunchedEffect(uiState.countCurrent) {
//                if (uiState.countTotal > 0) {
//                    try {
//                        withTimeout(7000) {
//                            // Wait until countCurrent becomes >= 0
//                            while (uiState.countCurrent < 0) {
//                                delay(100) // small polling delay
//                            }
//                        }
//                    } catch (e: TimeoutCancellationException) {
//                        // Timeout happened, still show loading or handle timeout
//                        Toast.makeText(context, "No Zikr Found Kindly Select one from the List", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//
//            if (uiState.countTotal > 0) {
//                // Data is ready
//                CustomComponent(
//                    indicatorValue = uiState.countCurrent,
//                    maxIndicatorValue = uiState.countTotal,
//                    foregroundIndicatorColor = brush2,
//                    backgroundIndicatorColor = brush,
//                    colorForCountbtn = IconBgTeal
//                )
//            } else{
//                // Loading UI
//
//                    Text(text = "Loading...", textAlign = TextAlign.Center,
//                        modifier = modifier.fillMaxWidth())
//
//            }

//            if (uiState.countTotal > 0) {
//                // Data is ready, safe to run your CustomComponent
//                CustomComponent(
//                    indicatorValue = uiState.countCurrent,
//                    maxIndicatorValue = uiState.countTotal,
//                    foregroundIndicatorColor = brush2,
//                    backgroundIndicatorColor = brush,
//                    colorForCountbtn = IconBgTeal,
//                )
//            } else {
//                // Show placeholder or loading UI here while waiting for data
//                // For example:
//                withTimeout(7000){
//                    Box(
//                        modifier = Modifier.fillMaxSize(),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Text(text = "Loading...", textAlign = TextAlign.Center)
//                    }
//                }
//            }


            //MainCounterCircle(incrementCount = { zikrControlModel.incrementCount(); })
            Spacer(Modifier.weight(1f))
            BottomAppBar(
                onNavigateList = onNavigateList, zikrControlModel = zikrControlModel,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)
            )



            if (uiState.showZikrCompletedDialog) {
                ZikrAlertDialog(
                    onDismissRequest = {
                        zikrControlModel.dismissZikrCompletedDialog() // Call the ViewModel function
                    },
                    onConfirmation = {
                        zikrControlModel.restartZikrAfterCompletion() // This already hides the dialog via the restart logic in ViewModel
                    },
                    dialogTitle = "Zikr Completed",
                    dialogText = "Restart or Cancel the Current Zikr!",
                    icon = painterResource(id = R.drawable.complete2),

                    iconDescription = "Reset Icon",
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
                    dialogText = "Reset the current Zikr count!",


                    icon = painterResource(id = R.drawable.reset),
                    iconDescription = "Reset Icon",
                    confirmButtonText = "Reset"
                )
            }


        }
    }


}

