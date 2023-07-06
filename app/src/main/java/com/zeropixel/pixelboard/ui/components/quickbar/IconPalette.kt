package com.zeropixel.pixelboard.ui.components.quickbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.zeropixel.pixelboard.canvas.IconProvider

@Composable
fun <T : IconProvider> IconPalette(
    palette: List<T>,
    isSelected: (T) -> Boolean = { false },
    onSelect: (T) -> Unit = {},
) {
    LazyColumn(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp)),

        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        items(palette) { tool ->
            IconItem(isSelected(tool), tool.icon()) {
                onSelect(tool)
            }
        }
    }
}

@Composable
fun IconItem(
    isSelected: Boolean,
    icon: ImageVector,
    onClick: () -> Unit,
) {
    val scale = if (isSelected) .9f else 1f

    Icon(
        icon,
        contentDescription = null,
        tint = Color.White,

        modifier = Modifier
            .scale(scale)
            .aspectRatio(1f)
            .clickable(onClick = onClick),
    )
}