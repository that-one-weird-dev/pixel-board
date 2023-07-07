package com.zeropixel.pixelboard.ui.components.menus

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zeropixel.pixelboard.ui.theme.Colors

class ConfirmMenuOptions(
    val message: String,
    val onCancel: () -> Unit = {},
    val onConfirm: () -> Unit,
)

@Composable
fun ConfirmMenu(message: String, onConfirm: () -> Unit, onCancel: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .clip(RoundedCornerShape(10.dp))
                .border(1.dp, color = Colors.Dark4, shape = RoundedCornerShape(10.dp))
                .background(Colors.Dark2)
                .padding(20.dp)
                .width(IntrinsicSize.Max),

            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = message,
                textAlign = TextAlign.Center,
                color = Colors.White1,
                fontSize = 20.sp,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Button(
                    onClick = onCancel,

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Colors.Dark4,
                        contentColor = Colors.White1,
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Cancel")
                }
                Button(
                    onClick = onConfirm,

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Colors.Dark4,
                        contentColor = Colors.White1,
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Confirm")
                }
            }
        }
    }
}