package com.example.zikrapp.ui.screens

import ZikrEditAddScreen
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
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
import com.example.zikrapp.ui.viewmodel.ZikrControlModel
import com.example.zikrapp.ui.viewmodel.ZikrDataModel
import kotlinx.serialization.Serializable

@Serializable
sealed interface ZikrRoute {
    @Serializable
    data class ZikrCount(val zikrId: Int = 0) : ZikrRoute
    @Serializable
    object ZikrList : ZikrRoute
    @Serializable
    data class ZikrEditAdd(val zikrId: Int = 1) : ZikrRoute
}
//
//
//enum class ZikrScreen() {
//
//    ZikrCount,
//    ZikrEditAdd,
//    ZikrList
//}



@Composable
fun ZikrApp(zikrControlModel: ZikrControlModel = viewModel(), zikrDataModel: ZikrDataModel = viewModel(),
            dataBaseViewModel: DataBaseViewModel = viewModel()) {
    val backStack = remember { mutableStateListOf<ZikrRoute>(ZikrRoute.ZikrCount()) }
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

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF1A1A1A))) {
        NavDisplay(
            backStack = backStack,
            onBack = { goBack() },
            entryProvider = { route ->
                when (route) {
                    is ZikrRoute.ZikrCount -> NavEntry(route) {
                        ZikrCountScreen(
                            onNavigateList = { navigate(ZikrRoute.ZikrList) },
                            modifier = Modifier,
                            zikrDataModel = zikrDataModel,
                            zikrControlModel = zikrControlModel,
                            currentZikrId = route.zikrId

                        )
                    }
                    ZikrRoute.ZikrList -> NavEntry(route) {
                        ZikrListScreen(
                            onAddZikr = { navigate(ZikrRoute.ZikrEditAdd(1)) },
                            onEditZikr = { id -> navigate(ZikrRoute.ZikrEditAdd(id)) },
                            onChangeZikr = { id -> navigate(ZikrRoute.ZikrCount(id)) },
                            zikrDataModel = zikrDataModel,

                        )
                    }
                    is ZikrRoute.ZikrEditAdd -> NavEntry(route) {
                        ZikrEditAddScreen(
                            zikrId = route.zikrId,
                            zikrDataModel = dataBaseViewModel,
                            onDone = { navigate(ZikrRoute.ZikrList) },
                            onCancel = { navigate(ZikrRoute.ZikrList) }
                        )
                    }
                }
            }
        )
    }
}







//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ZikrApp(
//    navController: NavHostController = rememberNavController(),
//    viewModel: ControlsViewModel = viewModel(), // you might want to fix this too
//    zikrViewModel: ZikrViewModel = viewModel()
//) {
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0XFF1A1A1A))
//    ) {
//
//        NavHost(
//            navController = navController,
//            startDestination = ZikrScreen.ZikrCount.name,
//            route = "main"
//        ){
//
//            composable(route = ZikrScreen.ZikrCount.name) {
//
//                ZikrCountScreen(navController = navController,viewModel = viewModel)
//            }
//
//            composable(route = ZikrScreen.ZikrList.name) {
//
//                ZikrListScreen(navController = navController,zkrViewModel = zikrViewModel)
//            }
//
//            composable(route = "${ZikrScreen.ZikrEditAdd.name}?zikrId={zikrId}",
//                arguments = listOf(navArgument("zikrId") { type = NavType.IntType
//                    defaultValue = 0 })) {
//
//                val zikrId = it.arguments?.getInt("zikrId") ?: 0
//                ZikrEditAddScreen(navController, zikrViewModel, zikrId)
//            }
//
//        }
//
//    }
//
//}





@Preview
@Composable
fun ZikrAppPreview() {
    ZikrApp()
}