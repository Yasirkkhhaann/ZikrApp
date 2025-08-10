package com.example.zikrapp.ui.screens

import ZikrEditAddScreen
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.zikrapp.ui.viewmodel.DataBaseViewModel
import kotlinx.serialization.Serializable

@Serializable
sealed interface ZikrRoute {
    @Serializable
    data class ZikrCount(val zikrId: Int) : ZikrRoute
    @Serializable
    object ZikrList : ZikrRoute
    @Serializable
    data class ZikrEditAdd(val zikrId: Int) : ZikrRoute
}

@Composable
fun ZikrApp(
            zikrControlModel: DataBaseViewModel = viewModel()) {
    val uiState by zikrControlModel.uiState.collectAsState()
    val backStack = remember { mutableStateListOf<ZikrRoute>(ZikrRoute.ZikrCount(zikrId = uiState.lastZikrId)) }
    val context = LocalContext.current
    fun navigate(to: ZikrRoute) = backStack.add(to)
    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    fun goBack(){


        when (backStack.last()) {
            is ZikrRoute.ZikrList -> {
                // Replace ZikrList with ZikrCount
                backStack.removeLast()
                val previousZikrId = if (backStack.lastOrNull() is ZikrRoute.ZikrCount) {
                    (backStack.last() as ZikrRoute.ZikrCount).zikrId
                } else {
                    0 // Default to zikrId = 0 if unknown
                }

                backStack.add(ZikrRoute.ZikrCount(previousZikrId))
            }

            is ZikrRoute.ZikrCount -> {
                backStack.clear()
                val activity = context as? ComponentActivity
                activity?.finish() // Closes the app
            }
            else -> {
                if (backStack.size > 1) backStack.removeLast()
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface)) {
        NavDisplay(
            backStack = backStack,
            onBack = { goBack() },
            entryProvider = { route ->
                when (route) {
                    is ZikrRoute.ZikrCount -> NavEntry(route) {
                        ZikrCountScreen(
                            onNavigateList = { navigate(ZikrRoute.ZikrList) },
                            modifier = Modifier,
                            zikrControlModel = zikrControlModel,
                        )
                    }
                    ZikrRoute.ZikrList -> NavEntry(route) {
                        ZikrListScreen(
                            onAddZikr = { navigate(ZikrRoute.ZikrEditAdd(0)) },
                            onEditZikr = { id -> navigate(ZikrRoute.ZikrEditAdd(id)) },
                            navigatToCountsreen = { navigate(ZikrRoute.ZikrCount(uiState.lastZikrId))}


                        )
                    }
                    is ZikrRoute.ZikrEditAdd -> NavEntry(route) {
                        ZikrEditAddScreen(
                            zikrId = route.zikrId,
                            onDone = { navigate(ZikrRoute.ZikrList) },
                            onCancel = { navigate(ZikrRoute.ZikrList) }
                        )
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun ZikrAppPreview() {
    ZikrApp()
}