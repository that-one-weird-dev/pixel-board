package com.zeropixel.pixelboard.ui.components.quickbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zeropixel.pixelboard.canvas.actions.Action
import com.zeropixel.pixelboard.canvas.tools.Tool

@Composable
fun QuickBar(
    modifier: Modifier = Modifier,

    currentColor: Color,
    colorPalette: List<Color>,
    onColorPick: (Color) -> Unit,

    currentTool: Tool,
    toolPalette: List<Tool>,
    onToolPick: (Tool) -> Unit,

    actionPalette: List<Action>,
    onActionPick: (Action) -> Unit,

    columns: Int = 1,
) {
    val columnWidth = 40
    val spacing = 5
    val width = (columns * columnWidth + (columns - 1) * spacing).dp

    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors()
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .width(width),

            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            ColorPalette(currentColor, colorPalette, onColorPick, columns = columns)
            Divider(color = MaterialTheme.colorScheme.onPrimaryContainer, thickness = 2.dp)
            IconPalette(
                toolPalette,
                isSelected = { it == currentTool },
                onToolPick,
                columns = columns,
            )
            Divider(color = MaterialTheme.colorScheme.onPrimaryContainer, thickness = 2.dp)
            IconPalette(actionPalette, onSelect = onActionPick, columns = columns)
        }
    }
}