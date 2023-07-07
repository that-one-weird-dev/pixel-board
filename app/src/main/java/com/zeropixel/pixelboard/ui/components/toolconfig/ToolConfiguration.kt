package com.zeropixel.pixelboard.ui.components.toolconfig

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ToolConfiguration(
    modifier: Modifier = Modifier,
    configuration: @Composable () -> Unit,
) {
    ElevatedCard(
        modifier = modifier
            .width(300.dp),
    ) {
        Column(
            modifier = Modifier.padding(10.dp, 0.dp),
        ) {
            configuration()
        }
    }
}