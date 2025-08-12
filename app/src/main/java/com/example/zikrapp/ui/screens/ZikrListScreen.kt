package com.example.zikrapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zikrapp.R
import com.example.zikrapp.ui.components.ZikrAlertDialog
import com.example.zikrapp.ui.components.ZikrListBanner
import com.example.zikrapp.ui.components.ZikrListItemCard
import com.example.zikrapp.ui.viewmodel.DataBaseViewModel


@Composable
fun ZikrListScreen(onAddZikr: () -> Unit, onEditZikr: (Int) -> Unit,
                   dbZikrDataModel: DataBaseViewModel = viewModel(),
                   wantToSaveNotSavedZikr: () -> Unit,
                   navigatToCountsreen: () -> Unit
                   ) {


    val zikrList by dbZikrDataModel.zikrlist.collectAsState()
    val uiState by dbZikrDataModel.uiState.collectAsState()


    Scaffold( containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            FloatingActionButton(onClick = { if(uiState.isLoadingforNew){
                dbZikrDataModel.showZikrNotSavedDialog()
            } else onAddZikr()},

                 modifier = Modifier.padding(end = 10.dp,bottom = 10.dp)
                    ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {


        Column (modifier = Modifier.padding(it)) {


           ZikrListBanner()

            if (uiState.showZikrNotSavedDialog) {
                ZikrAlertDialog(
                    onDismissRequest = {
                        dbZikrDataModel.dismissZikrNotSavedDialog() // Call the ViewModel function

                    },
                    onConfirmation = {
                        wantToSaveNotSavedZikr()  // This already hides the dialog via the restart logic in ViewModel
                        dbZikrDataModel.dismissZikrNotSavedDialog()
                    },
                    dialogTitle = "Please Confirm",
                    dialogText = "Do You Want To Save The Current Counts?",
                    icon = painterResource(id = R.drawable.complete2),

                    iconDescription = "Reset Icon",
                    confirmButtonText = "Yes",
                    dismissButtonText = "No"
                )
            }

            if (uiState.showDeleleDialog) {
            ZikrAlertDialog(
                onDismissRequest = {
                    dbZikrDataModel.dismissDeleltDialog() // Call the ViewModel function
                },
                onConfirmation = {
                    dbZikrDataModel.dismissDeleltDialog()
                    dbZikrDataModel.deleteZikr(uiState.setzikrIdForDelete)

                // This already hides the dialog via the restart logic in ViewModel

                },
                dialogTitle = "Please Confirm",
                dialogText = "Do You Want To Delete This Zikr?",
                icon = painterResource(id = R.drawable.delete),

                iconDescription = "Delete",
                confirmButtonText = "Yes",
                dismissButtonText = "No"
            )
        }
Spacer(modifier = Modifier.height(10.dp))


            zikrList.let {

                LazyColumn(
                    contentPadding = PaddingValues(bottom = 30.dp)
                ) {
                    items(zikrList) { zikr ->
                        Spacer(modifier = Modifier.height(10.dp))
                        ZikrListItemCard(
                            onEditZikr = onEditZikr,
                            navigatToCountsreen = navigatToCountsreen,
                            zikr = zikr,
                            dataBaseViewModel = dbZikrDataModel
                        )
                    }
                }


            }

        }
    }

}



//@Preview
//@Composable
//fun ZikrListScreenPreview() {
//    ZikrListScreen(navController = NavController(LocalContext.current))
//}