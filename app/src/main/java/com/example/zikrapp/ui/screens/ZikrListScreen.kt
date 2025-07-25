package com.example.zikrapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.example.zikrapp.ui.viewmodel.ZikrDataModel


@Composable
fun ZikrListScreen(onAddZikr: () -> Unit, onEditZikr: (Int) -> Unit,
                   onChangeZikr: (Int) -> Unit,
                   dbZikrDataModel: DataBaseViewModel = viewModel()
                   ) {


    val zikrList by dbZikrDataModel.zikrlist.observeAsState(emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onAddZikr() },
                containerColor = Color(0xff3a3838), modifier = Modifier.padding(end = 10.dp,bottom = 10.dp)) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier.padding(innerPadding).verticalScroll(rememberScrollState()),
        ) {

            ZikrListBanner()

            Spacer(modifier = Modifier.height(25.dp))



            zikrList?.let {
                LazyColumn(
                    modifier = Modifier.height(425.dp),
                    contentPadding = PaddingValues(bottom = 30.dp)
                ) {
                    items(zikrList.size) { it
                        val zikr = zikrList[it]

                        Spacer(modifier = Modifier.height(10.dp))
                        ZikrListItemCard(onChangeZikr, onEditZikr,zikr = zikr,dbZikrDataModel)
                    }


                }

            } ?: Text(
                text = "No Data",
                style = TextStyle(
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                ),
                modifier = Modifier.fillMaxWidth()
            )

        }
    }

}



//@Preview
//@Composable
//fun ZikrListScreenPreview() {
//    ZikrListScreen(navController = NavController(LocalContext.current))
//}