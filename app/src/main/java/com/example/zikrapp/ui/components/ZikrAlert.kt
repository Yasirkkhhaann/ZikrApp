package com.example.zikrapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.zikrapp.R







@Composable
fun BrushAlertDialog(
    onDismiss: () -> Unit,
    brush: Brush,
    content: @Composable () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            Modifier
                .background(brush, shape = MaterialTheme.shapes.medium)
                .padding(24.dp)
        ) {

            Dialog(onDismissRequest = onDismiss,
                ) {
                Box(
                    Modifier
                        .background(brush, shape = MaterialTheme.shapes.medium)
                        .padding(24.dp)
                ) {

                }
            }
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZikrAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    icon: Painter,
    iconDescription: String,
    color: Color = MaterialTheme.colorScheme.onSurface,
    dialogTitle: String, // New parameter
    dialogText: String,  // New parameter
    confirmButtonText: String = "Confirm", // Optional: allow customizing confirm button text
    dismissButtonText: String = "Cancel"

) {
    val colors = arrayOf(
        0.1f to MaterialTheme.colorScheme.surfaceVariant,
        0.9f to MaterialTheme.colorScheme.primary,
    )
    val brush = Brush.horizontalGradient(colorStops = colors)





    AlertDialog(
        icon = {
            Icon(icon, contentDescription = iconDescription)
        },
        title = {
            Text(text = dialogTitle, color = color)
        },
        text = {
            Text(text = dialogText, color = color)
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
                Text(text = confirmButtonText, color = color)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(text = dismissButtonText, color = color)
            }
        }
    )}



//@Preview (showBackground = true)
//@Composable
//fun BrushAlertDialogPreview() {
//    BrushAlertDialog
@Preview
@Composable
fun AlertDialogExamplePreview() {
    ZikrAlertDialog(
        onDismissRequest = {},
        onConfirmation = {},
        icon = painterResource(id = R.drawable.complete2),
        iconDescription = "Info Icon",
        dialogTitle = "Example Title",
        dialogText = "This is an example text for the dialog.",
        confirmButtonText = "Yes",
        dismissButtonText = "No"
    )
}