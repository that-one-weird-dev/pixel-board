package com.zeropixel.pixelboard.ui.components.menus

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.zeropixel.pixelboard.utils.AlertDialogOptions

@Composable
fun ConfirmDialog(
    visible: Boolean,
    options: AlertDialogOptions,
    onClose: () -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
    ) {
        Dialog(
            options.message,
            onCancel = {
                options.onCancel()
                onClose()
            },
            onConfirm = {
                options.onConfirm()
                onClose()
            }
        )
    }
}

@Composable
private fun Dialog(message: String, onConfirm: () -> Unit, onCancel: () -> Unit) {
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