package com.example.zikrapp.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zikrapp.R
import com.example.zikrapp.ui.viewmodel.DataBaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun DropdownMenuWithDetails(onEditZikr: (Int) -> Unit,zikrid: Int,
                            navigatToCountsreen: () -> Unit,
    dataBaseViewModel: DataBaseViewModel = viewModel(),) {
    var expanded by remember { mutableStateOf(false) }


    val coroutine = CoroutineScope(Dispatchers.IO)
    Box(
        modifier = Modifier
            .fillMaxWidth(),

        contentAlignment = Alignment.TopStart
    ) {
        IconButton(onClick = { expanded = !expanded },
            modifier = Modifier) {
            Icon(painter = painterResource(id = R.drawable.dropdown)
                ,tint = Color.White, contentDescription = "Drop down icon",
                modifier = Modifier.size(31.dp))
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },

            modifier = Modifier
                .background(Color(0xff454040)),
        ) {
            // First section
            DropdownMenuItem(
                text = { Text("Continue    " , style = TextStyle(color = Color.White)) },
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.resource_continue), tint = Color.White, contentDescription = null,
                    modifier = Modifier.size(15.dp)) },
                onClick = { coroutine.launch { dataBaseViewModel.setLastZikr(zikrid) }; navigatToCountsreen() }
            )
            DropdownMenuItem(
                text = { Text("Edit    ", style = TextStyle(color = Color.White)) },
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.edit), tint = Color.White, contentDescription = null,
                    modifier = Modifier.size(15.dp)) },
                onClick = { onEditZikr(zikrid) }
            )

            // Second section
            DropdownMenuItem(
                text = { Text("Delete    ", style = TextStyle(color = Color.White)) },
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.delete), tint = Color.White, contentDescription = null,
                    modifier = Modifier.size(15.dp)) },
                onClick = { dataBaseViewModel.deleteZikr(zikrid) }
            )

            // Third section
            DropdownMenuItem(
                text = { Text("Reset    ", style = TextStyle(color = Color.White)) },
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.reset), tint = Color.White, contentDescription = null,
                    modifier = Modifier.size(15.dp)) },
                onClick = { /* Do something... */ }
            )
        }
    }
}



