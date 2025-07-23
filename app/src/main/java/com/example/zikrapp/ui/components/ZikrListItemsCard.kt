package com.example.zikrapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zikrapp.ui.viewmodel.Zikr
import com.example.zikrapp.ui.viewmodel.ZikrDataModel


@Composable
fun ZikrListItemCard(
                     changeZikr: (Int) -> Unit,onEdit: (Int) -> Unit,zikr: Zikr,zikrDataModel: ZikrDataModel = viewModel()) {

    ElevatedCard(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(Color(0XFF262626)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .size(175.dp)
            .padding(start = 25.dp, end = 25.dp)
    ) {

        Column (modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.weight(0.9f)) {


                Row(modifier = Modifier.fillMaxSize()) {

                    Column(modifier = Modifier.weight(8f).fillMaxSize()) {


                        Row(modifier = Modifier.fillMaxWidth(),) {

                            Text(
                                "Count :",
                                color = Color.White,
                                modifier = Modifier.weight(0.4f).fillMaxWidth()
                                    .padding(start = 20.dp, top = 20.dp, end = 5.dp),
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    textAlign = TextAlign.End,
                                    letterSpacing = 2.sp,
                                    fontWeight = FontWeight.Light,
                                )
                            )

                            Text(
                                "${zikr.zikrCountStart} / ${zikr.zikrCountEnd}", color = Color.White,
                                modifier = Modifier.weight(0.6f).fillMaxWidth()
                                    .padding(top = 20.dp), style = TextStyle(
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Light,
                                )
                            )
                        }

                        Row(modifier = Modifier.fillMaxWidth(),) {

                            Text(
                                "Name :",
                                color = Color.White,
                                modifier = Modifier.weight(0.4f).fillMaxWidth()
                                    .padding(start = 20.dp, top = 5.dp, end = 5.dp),
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    textAlign = TextAlign.End,
                                    letterSpacing = 2.sp,
                                    fontWeight = FontWeight.Light,
                                )
                            )

                            Text(
                                "${zikr.zikrName}",
                                color = Color.White,
                                modifier = Modifier.weight(0.6f).fillMaxWidth().padding(top = 5.dp),
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    letterSpacing = 2.sp,
                                    fontWeight = FontWeight.Light,
                                )
                            )
                        }

                        Row(modifier = Modifier.fillMaxWidth(),) {

                            Text(
                                "Note :",
                                color = Color.White,
                                modifier = Modifier.weight(0.4f).fillMaxWidth()
                                    .padding(start = 20.dp, top = 5.dp, end = 5.dp),
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    textAlign = TextAlign.End,
                                    letterSpacing = 2.sp,
                                    fontWeight = FontWeight.Light,
                                )
                            )

                            Text(
                                "${zikr.zikrDescription}",color = Color.White,
                                modifier = Modifier.weight(0.6f).fillMaxWidth().padding(top = 5.dp),
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    letterSpacing = 1.sp,
                                    textAlign = TextAlign.Start,
                                    fontWeight = FontWeight.Light,
                                )
                            )
                        }
                    }



                    Column(
                        modifier = Modifier.weight(2f).fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        DropdownMenuWithDetails(changeZikr,onEdit,zikrid = zikr.zikrId, zikrDataModel = zikrDataModel)

                    }
                }
            }

            Row(modifier = Modifier.weight(0.06f)) {

                Row(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
                            .background(Color(0xFF38B6A5), RoundedCornerShape(2.dp))
                    ) {
                    }
                }
            }


        }
    }
}


//@Preview
//@Composable
//fun ZikrListItemCardPreview() {
//    ZikrListItemCard( navController = NavController(LocalContext.current),zikr = Zikr(1,"","",0,50))
//}