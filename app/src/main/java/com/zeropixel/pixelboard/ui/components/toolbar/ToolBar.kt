package com.zeropixel.pixelboard.ui.components.toolbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zeropixel.pixelboard.canvas.tools.Tool
import com.zeropixel.pixelboard.ui.components.IconPalette

@Composable
fun ToolBar(
    modifier: Modifier = Modifier,

    currentTool: Tool,
    toolPalette: List<Tool>,
    onToolPick: (Tool) -> Unit,
) {
    ElevatedCard(
        modifier = modifier
            .width(300.dp),
    ) {
        Column(
            modifier = Modifier.padding(10.dp),

            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            IconPalette(
                palette = toolPalette,
                isSelected = { it == currentTool },
                onSelect = onToolPick,
            )
            currentTool.configuration?.let { configuration ->
                Divider(color = MaterialTheme.colorScheme.onPrimaryContainer)
                Column {
                    configuration()
                }
            }
        }
    }
}