package com.zeropixel.pixelboard.ui.components.quickbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.zeropixel.pixelboard.canvas.IconProvider

@Composable
fun <T : IconProvider> IconPalette(
    palette: List<T>,
    isSelected: (T) -> Boolean = { false },
    onSelect: (T) -> Unit = {},

    columns: Int = 1,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),

        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        items(palette) { tool ->
            IconItem(isSelected(tool), tool.icon()) {
                onSelect(tool)
            }
        }
    }
}

@Composable
private fun IconItem(
    isSelected: Boolean,
    icon: ImageVector,
    onClick: () -> Unit,
) {
    val scale = if (isSelected) .9f else 1f

    Icon(
        icon,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.onPrimaryContainer,

        modifier = Modifier
            .size(40.dp)
            .scale(scale)
            .aspectRatio(1f)
            .clickable(onClick = onClick),
    )
}