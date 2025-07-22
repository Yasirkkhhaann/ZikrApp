package com.example.zikrapp.ui.screens

import ZikrEditAddScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.zikrapp.ui.viewmodel.ControlsViewModel
import com.example.zikrapp.ui.viewmodel.ZikrViewModel


enum class ZikrScreen() {

    ZikrCount,
    ZikrEditAdd,
    ZikrList
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZikrApp(
    navController: NavHostController = rememberNavController(),
    viewModel: ControlsViewModel = viewModel(), // you might want to fix this too
    zikrViewModel: ZikrViewModel = viewModel()
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0XFF1A1A1A))
    ) {

        NavHost(
            navController = navController,
            startDestination = ZikrScreen.ZikrCount.name,
            route = "main"
        ){

            composable(route = ZikrScreen.ZikrCount.name) {

                ZikrCountScreen(navController = navController,viewModel = viewModel)
            }

            composable(route = ZikrScreen.ZikrList.name) {

                ZikrListScreen(navController = navController,zkrViewModel = zikrViewModel)
            }

            composable(route = "${ZikrScreen.ZikrEditAdd.name}?zikrId={zikrId}",
                arguments = listOf(navArgument("zikrId") { type = NavType.IntType
                    defaultValue = 0 })) {

                val zikrId = it.arguments?.getInt("zikrId") ?: 0
                ZikrEditAddScreen(navController, zikrViewModel, zikrId)
            }

        }

    }

}





@Preview
@Composable
fun ZikrAppPreview() {
    ZikrApp()
}