package com.example.zikrapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zikrapp.ui.components.ZikrListBanner
import com.example.zikrapp.ui.components.ZikrListItemCard
import com.example.zikrapp.ui.viewmodel.DataBaseViewModel


@Composable
fun ZikrListScreen(onAddZikr: () -> Unit, onEditZikr: (Int) -> Unit,
                   dbZikrDataModel: DataBaseViewModel = viewModel(),
                   navigatToCountsreen: () -> Unit
                   ) {


    val zikrList by dbZikrDataModel.zikrlist.collectAsState()



    Scaffold( containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            FloatingActionButton(onClick = { onAddZikr()},

                 modifier = Modifier.padding(end = 10.dp,bottom = 10.dp)
                    ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {


        Column (modifier = Modifier.padding(it)) {


           ZikrListBanner()
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