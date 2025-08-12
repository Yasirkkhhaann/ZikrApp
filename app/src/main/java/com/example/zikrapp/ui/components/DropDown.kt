package com.example.zikrapp.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zikrapp.R
import com.example.zikrapp.ui.theme1.ui.theme.TealBottom
import com.example.zikrapp.ui.theme1.ui.theme.TealTop
import com.example.zikrapp.ui.viewmodel.DataBaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun DropdownMenuWithDetails(
    onEditZikr: (Int) -> Unit, zikrid: Int,
    navigatToCountsreen: () -> Unit,
    dataBaseViewModel: DataBaseViewModel = viewModel(),
) {
    var expanded by remember { mutableStateOf(false) }
    val uiState by dataBaseViewModel.uiState.collectAsState()

    val coroutine = CoroutineScope(Dispatchers.IO)
    Box(
        modifier = Modifier
            .fillMaxWidth(),

        contentAlignment = Alignment.TopStart
    ) {
        IconButton(
            onClick = { expanded = !expanded },
            modifier = Modifier
        ) {
            Icon(
                painter = painterResource(id = R.drawable.dropdown),
                tint = Color.Black,
                contentDescription = "Drop down icon",
                modifier = Modifier.size(31.dp)
            )
        }
        val colors = arrayOf(


            0.1f to MaterialTheme.colorScheme.surfaceVariant,
            0.9f to MaterialTheme.colorScheme.primary,
        )
        val brush2 = Brush.verticalGradient(
            colors = listOf(TealTop, TealBottom)
        )
        val brush = Brush.horizontalGradient(colorStops = colors)

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },

            modifier = Modifier
                .background(brush = brush2),
        ) {

//            onClick = { if(uiState.lastZikrId == 0){
//                dbZikrDataModel.showZikrNotSavedDialog()
//            } else onAddZikr()}

            // First section
            DropdownMenuItem(
                text = { Text("Continue    ", color = MaterialTheme.colorScheme.onSurface) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.resource_continue),
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp)
                    )
                },
                onClick = {
                    expanded = false;

                    if(uiState.isLoadingforNew){
                        dataBaseViewModel.showZikrNotSavedDialog()
                    }

                    coroutine.launch {
                    dataBaseViewModel.setLastZikr(zikrid);
                    dataBaseViewModel.updatesaveState()
                };
                    dataBaseViewModel.updatelastZikrId(zikrid);
                    dataBaseViewModel.unloadCountforOldZikr();
                    dataBaseViewModel.updateNotSavedCount(0)
                    navigatToCountsreen()
                })
            DropdownMenuItem(
                text = { Text("Edit    ", color = MaterialTheme.colorScheme.onSurface) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp)
                    )
                },
                onClick = {
                    expanded =
                        false;
                    if(uiState.isLoadingforNew){
                        dataBaseViewModel.showZikrNotSavedDialog()
                    }
                    onEditZikr(zikrid);
                    dataBaseViewModel.updateNotSavedCount(0)
                    coroutine.launch { dataBaseViewModel.updatesaveState() }
                }
            )

            // Second section
            DropdownMenuItem(
                text = { Text("Delete    ", color = MaterialTheme.colorScheme.onSurface) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.delete),
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp)
                    )
                },
                onClick = {
                    expanded = false;

                    dataBaseViewModel.setzikrIdForDelete(zikrid)
                    dataBaseViewModel.showDeleltDialog()
                }
            )

            // Third section
            DropdownMenuItem(
                text = { Text("Reset    ", color = MaterialTheme.colorScheme.onSurface) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.reset),
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp)
                    )
                },
                onClick = {
                    expanded =
                        false; dataBaseViewModel.resetZikr(zikrid);coroutine.launch { dataBaseViewModel.updatesaveState() }
                }
            )
        }
    }
}



