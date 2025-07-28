package com.example.zikrapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zikrapp.R
import com.example.zikrapp.data.Zikr
import com.example.zikrapp.data.ZikrUiStatee
import com.example.zikrapp.ui.components.ActionRow
import com.example.zikrapp.ui.components.BottomAppBar
import com.example.zikrapp.ui.components.TopAppBar
import com.example.zikrapp.ui.components.MainCounterCircle
import com.example.zikrapp.ui.components.ZikrAlertDialog
import com.example.zikrapp.ui.components.ZikrName
import com.example.zikrapp.ui.components.ZikrNote
import com.example.zikrapp.ui.viewmodel.DataBaseViewModel
import com.example.zikrapp.ui.viewmodel.ZikrControlModel
import com.example.zikrapp.ui.viewmodel.ZikrDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.last
import kotlin.math.absoluteValue


@SuppressLint("UnrememberedMutableState")
@Composable
fun ZikrCountScreen(
    modifier: Modifier = Modifier,
    onNavigateList: () -> Unit,
    zikrControlModel: ZikrControlModel = viewModel(),
    databaseViewModel: DataBaseViewModel = viewModel(),
    onLeaveScreen: (Int,Int) -> Unit
    
) {
    val uiState by zikrControlModel.uiState.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val datastore = remember { ZikrUiStatee(context) }

    val zikrId by datastore.zikrId.collectAsStateWithLifecycle(3)
    // Remember latest values to use inside lifecycle observer
    val currentCount by rememberUpdatedState(uiState.countCurrent)
    // Collect the Flow<Zikr?> as State<Zikr?> reactively
    var zikrFlow by remember { mutableStateOf<Flow<Zikr?>?>(null) }

    LaunchedEffect(uiState.lastZikrId) {
        zikrControlModel.updatelastZikrId(zikrFlow?.last()?.zikrId?:0)
        zikrFlow = databaseViewModel.getZikrById(uiState.lastZikrId) // call suspend function inside coroutine
    }

    val zikr by zikrFlow?.collectAsState(initial = null) ?: mutableStateOf(null)

    var zikrName by remember { mutableStateOf("") }
    var zikrStart by remember { mutableStateOf(0) }
    var zikrEnd by remember { mutableStateOf(0) }
    var zikrDescription by remember { mutableStateOf("") }

    LaunchedEffect(zikr) {
        zikrName = zikr?.zikrName ?: ""
        zikrStart = zikr?.zikrCountStart?: 0
        zikrEnd = zikr?.zikrCountEnd?: 0
        zikrDescription = zikr?.zikrDescription ?: ""

        zikrControlModel.loadZikrBounds(zikrStart,zikrEnd)

//        if(zikrId != 0){
//
//            zikrControlModel.settocurrent(zikrId,uiState.countCurrent)
//        }

    }



    // 1. Handle navigation away (composable disposed)
    DisposableEffect(Unit) {
        onDispose {
            onLeaveScreen(uiState.lastZikrId, currentCount)
        }
    }

    // 2. Handle app background/close events
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP) {
                // App goes to background or closes
                onLeaveScreen(uiState.lastZikrId, currentCount)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
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
        ActionRow( uiState.countCurrent,
            uiState.countTotal,
            onResetClick = { zikrControlModel.showResetConfirmationDialog() },
            modifier = modifier.fillMaxWidth(),
            zikrControlModel = zikrControlModel
        )
        Spacer(Modifier.height(10.dp))
        MainCounterCircle(incrementCount = {zikrControlModel.incrementCount()})
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



