package com.zeropixel.pixelboard.components.toolbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zeropixel.pixelboard.components.IconPalette
import com.zeropixel.pixelboard.components.shared.Panel
import com.zeropixel.pixelboard.utils.ComposableTool

@Composable
fun ToolBar(
    modifier: Modifier = Modifier,

    currentTool: ComposableTool<*>,
    toolPalette: List<ComposableTool<*>>,
    onToolPick: (ComposableTool<*>) -> Unit,
) {
    Panel(
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
            currentTool.configuration?.let {
                Divider(color = Color.Black, thickness = 2.dp)
                Column {
                    currentTool.Configure()
                }
            }
        }
    }
}