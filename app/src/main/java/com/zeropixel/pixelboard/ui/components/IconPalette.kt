package com.zeropixel.pixelboard.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.zeropixel.pixelboard.canvas.IconProvider

@Composable
fun <T : IconProvider> IconPalette(
    modifier: Modifier = Modifier,
    palette: List<T>,
    isSelected: (T) -> Boolean = { false },
    onSelect: (T) -> Unit = {},
) {
    LazyRow(
        modifier = modifier,

        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        items(palette) { tool ->
            IconItem(
                isSelected = isSelected(tool),
                icon = tool.icon(),
            ) {
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
    val color = if (isSelected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary
    val onColor = if (isSelected) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.onPrimary

    IconButton(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(color),

        onClick = onClick,
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = onColor,

            modifier = Modifier
                .size(40.dp)
                .aspectRatio(1f)
                .clickable(onClick = onClick),
        )
    }
}