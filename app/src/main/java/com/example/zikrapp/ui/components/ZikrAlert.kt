package com.example.zikrapp.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.zikrapp.R

@Composable
fun ZikrAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
icon: Painter,
    iconDescription: String
    ,
    color: Color,
    dialogTitle: String, // New parameter
    dialogText: String,  // New parameter
    confirmButtonText: String = "Confirm", // Optional: allow customizing confirm button text
    dismissButtonText: String = "Cancel"

) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = iconDescription, tint = color)
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(text = confirmButtonText)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(text = dismissButtonText)
            }
        }
    )
}


@Preview
@Composable
fun AlertDialogExamplePreview(){
    ZikrAlertDialog(
        onDismissRequest = {},
        onConfirmation = {},
        icon = painterResource(id = R.drawable.complete2),
        iconDescription = "Info Icon",
        color = Color.Green,
        dialogTitle = "Example Title",
        dialogText = "This is an example text for the dialog.",
        confirmButtonText = "Yes",
        dismissButtonText = "No")
}