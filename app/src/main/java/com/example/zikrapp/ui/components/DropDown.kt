package com.example.zikrapp.ui.components


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.outlined.Feedback
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zikrapp.R
import com.example.zikrapp.ui.viewmodel.Zikr
import com.example.zikrapp.ui.viewmodel.ZikrDataModel

@Composable
fun DropdownMenuWithDetails(
    onChangeZikr: (Int) -> Unit,onEditZikr: (Int) -> Unit,zikrid: Int,zikrDataModel: ZikrDataModel= viewModel()) {
    var expanded by remember { mutableStateOf(false) }



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
                .background(Color(0XFF262626)),
            offset = DpOffset(-35.dp,0.dp)
        ) {
            // First section
            DropdownMenuItem(
                text = { Text("Continue    " , style = TextStyle(color = Color.White)) },
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.resource_continue), tint = Color.White, contentDescription = null,
                    modifier = Modifier.size(15.dp)) },
                onClick = { onChangeZikr(zikrid) }
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
                onClick = { zikrDataModel.deleteZikr(zikrid) }
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





@Composable
fun CenteredDropdownMenu() {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart) {
        IconButton(onClick = { expanded = true }) {
            Icon(
                painter = painterResource(id = R.drawable.dropdown),
                tint = Color.White,
                contentDescription = "More options",
                modifier = Modifier.size(31.dp)
            )
        }

        if (expanded) {
            Popup(
                alignment = Alignment.Center,
                onDismissRequest = { expanded = false },
                properties = PopupProperties(focusable = true)
            ) {
                Surface(
                    modifier = Modifier
                        .width(200.dp)
                        .wrapContentHeight()
                        .background(Color.Blue),
                    shape = MaterialTheme.shapes.medium,
                    tonalElevation = 8.dp
                ) {
                    Column {
                        DropdownMenuItem(
                            text = { Text("Profile", color = Color.White) },
                            onClick = { expanded = false }
                        )
                        DropdownMenuItem(
                            text = { Text("Settings", color = Color.White) },
                            onClick = { expanded = false }
                        )
                        DropdownMenuItem(
                            text = { Text("Send Feedback", color = Color.White) },
                            onClick = { expanded = false }
                        )
                        DropdownMenuItem(
                            text = { Text("About", color = Color.White) },
                            onClick = { expanded = false }
                        )
                        DropdownMenuItem(
                            text = { Text("Help", color = Color.White) },
                            onClick = { expanded = false }
                        )
                    }
                }
            }
        }
    }
}



@Composable
fun DropdownMenuWithDetails2() {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopStart
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                painter = painterResource(id = R.drawable.dropdown),
                tint = Color.White,
                contentDescription = "Drop down icon",
                modifier = Modifier.size(31.dp)
            )
        }

        if (expanded) {
            Popup(
                alignment = Alignment.Center,
                onDismissRequest = { expanded = false },
                properties = PopupProperties(focusable = true)
            ) {
                Surface(
                    modifier = Modifier
                        .width(250.dp)
                        .wrapContentHeight()
                        .background(Color(0xFF262626)),
                    shape = MaterialTheme.shapes.medium,
                    tonalElevation = 8.dp
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        DropdownMenuItem(
                            text = { Text("Continue", color = Color.White) },
                            leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = null, tint = Color.White) },
                            onClick = { expanded = false }
                        )
                        DropdownMenuItem(
                            text = { Text("Settings", color = Color.White) },
                            leadingIcon = { Icon(Icons.Outlined.Settings, contentDescription = null, tint = Color.White) },
                            onClick = { expanded = false }
                        )
                        DropdownMenuItem(
                            text = { Text("Send Feedback", color = Color.White) },
                            leadingIcon = { Icon(Icons.Outlined.Feedback, contentDescription = null, tint = Color.White) },
                            onClick = { expanded = false }
                        )
                        DropdownMenuItem(
                            text = { Text("About", color = Color.White) },
                            leadingIcon = { Icon(Icons.Outlined.Info, contentDescription = null, tint = Color.White) },
                            onClick = { expanded = false }
                        )
                        DropdownMenuItem(
                            text = { Text("Help", color = Color.White) },
                            leadingIcon = { Icon(Icons.AutoMirrored.Outlined.Help, contentDescription = null, tint = Color.White) },
                            onClick = { expanded = false }
                        )
                    }
                }
            }
        }
    }
}



//@Preview
//@Composable
//fun DropdownMenuWithDetailsPreview() {
//    DropdownMenuWithDetails()
//}