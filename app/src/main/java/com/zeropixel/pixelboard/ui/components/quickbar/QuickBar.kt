package com.zeropixel.pixelboard.ui.components.quickbar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zeropixel.pixelboard.canvas.actions.Action
import com.zeropixel.pixelboard.canvas.tools.Tool
import com.zeropixel.pixelboard.ui.theme.Colors

@Composable
fun QuickBar(
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

    Column(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Colors.Dark2)
            .border(1.dp, Colors.Dark4, shape = RoundedCornerShape(10.dp))
            .padding(10.dp)
            .width(width),

        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        ColorPalette(currentColor, colorPalette, onColorPick, columns = columns)
        Divider(color = Colors.Dark4, thickness = 2.dp)
        IconPalette(
            toolPalette,
            isSelected = { it == currentTool },
            onToolPick,
            columns = columns,
        )
        Divider(color = Colors.Dark4, thickness = 2.dp)
        IconPalette(actionPalette, onSelect = onActionPick, columns = columns)
    }
}