package com.zeropixel.pixelboard.ui.components.menus

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class ConfirmMenuOptions(
    val message: String,
    val onCancel: () -> Unit = {},
    val onConfirm: () -> Unit,
)

@Composable
fun ConfirmMenu(message: String, onConfirm: () -> Unit, onCancel: () -> Unit) {
    AlertDialog(

        confirmButton = {
            Button(
                onClick = onConfirm, shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Confirm")
            }
        },
        dismissButton = {
            Button(
                onClick = onCancel, shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Cancel")
            }
        },

        text = {
            Text(
                text = message,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
            )
        },

        onDismissRequest = onCancel,
    )
}