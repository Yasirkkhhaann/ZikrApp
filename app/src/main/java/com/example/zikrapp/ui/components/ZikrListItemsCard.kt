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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zikrapp.data.Zikr
import com.example.zikrapp.ui.theme1.ui.theme.TealBottom
import com.example.zikrapp.ui.theme1.ui.theme.TealTop
import com.example.zikrapp.ui.viewmodel.DataBaseViewModel


@Composable
fun ZikrListItemCard(
    onEditZikr: (Int) -> Unit,
    navigatToCountsreen: () -> Unit, zikr: Zikr, dataBaseViewModel: DataBaseViewModel = viewModel()
) {

    ElevatedCard(
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .size(130.dp)
            .padding(start = 25.dp, end = 25.dp)
    ) {
        val colors = arrayOf(


            0.1f to TealTop,
            0.9f to TealBottom,
        )

        val brush = Brush.linearGradient(colorStops = colors)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = brush
                )
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(modifier = Modifier.weight(0.9f)) {


                    Row(modifier = Modifier.fillMaxSize()) {

                        Column(
                            modifier = Modifier
                                .weight(8f)
                                .fillMaxSize()
                        ) {


                            Row(modifier = Modifier.fillMaxWidth()) {

                                Text(
                                    "Count :",
                                    color = Color.Black,
                                    modifier = Modifier
                                        .weight(0.4f)
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, top = 20.dp, end = 5.dp),
                                    style = TextStyle(
                                        fontSize = 13.sp,
                                        textAlign = TextAlign.End,
                                        letterSpacing = 2.sp,
                                        fontWeight = FontWeight.Light,
                                        color = Color.Black
                                    )
                                )

                                Text(
                                    "${zikr.zikrCountStart} / ${zikr.zikrCountEnd}",
                                    color = Color.Black,
                                    modifier = Modifier
                                        .weight(0.6f)
                                        .fillMaxWidth()
                                        .padding(top = 20.dp),
                                    style = TextStyle(
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Light,
                                        color = Color.Black

                                    )
                                )
                            }

                            Row(modifier = Modifier.fillMaxWidth()) {

                                Text(
                                    "Name :",
                                    color = Color.Black,

                                    modifier = Modifier
                                        .weight(0.4f)
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, top = 5.dp, end = 5.dp),
                                    style = TextStyle(
                                        fontSize = 13.sp,
                                        textAlign = TextAlign.End,
                                        letterSpacing = 2.sp,
                                        fontWeight = FontWeight.Light,
                                        color = Color.Black
                                    )
                                )

                                Text(
                                    zikr.zikrName,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .weight(0.6f)
                                        .fillMaxWidth()
                                        .padding(top = 5.dp),
                                    style = TextStyle(
                                        fontSize = 13.sp,
                                        letterSpacing = 2.sp,
                                        fontWeight = FontWeight.Light,
                                        color = Color.Black
                                    )
                                )
                            }

                            Row(modifier = Modifier.fillMaxWidth()) {

                                Text(
                                    "Note :",
                                    color = Color.Black,
                                    modifier = Modifier
                                        .weight(0.4f)
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, top = 5.dp, end = 5.dp),
                                    style = TextStyle(
                                        fontSize = 13.sp,
                                        textAlign = TextAlign.End,
                                        letterSpacing = 2.sp,
                                        fontWeight = FontWeight.Light,
                                        color = Color.Black
                                    )
                                )

                                Text(
                                    zikr.zikrDescription,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .weight(0.6f)
                                        .fillMaxWidth()
                                        .padding(top = 5.dp),
                                    style = TextStyle(
                                        fontSize = 13.sp,
                                        letterSpacing = 1.sp,
                                        textAlign = TextAlign.Start,
                                        fontWeight = FontWeight.Light,
                                        color = Color.Black,
                                    )
                                )
                            }
                        }



                        Column(
                            modifier = Modifier
                                .weight(2f)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            DropdownMenuWithDetails(
                                onEditZikr,
                                zikrid = zikr.zikrId,
                                navigatToCountsreen,
                                dataBaseViewModel = dataBaseViewModel
                            )

                        }
                    }
                }

                Row(modifier = Modifier.weight(0.06f)) {

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    RoundedCornerShape(2.dp)
                                )
                        ) {
                        }
                    }
                }


            }
        }
    }
}


@Preview
@Composable
fun ZikrListItemCardPreview() {
    ZikrListItemCard(onEditZikr = {}, navigatToCountsreen = {}, zikr = Zikr(1, "", "", 1, 100))
}