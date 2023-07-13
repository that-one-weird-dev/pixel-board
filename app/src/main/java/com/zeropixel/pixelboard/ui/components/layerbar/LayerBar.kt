package com.zeropixel.pixelboard.ui.components.layerbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zeropixel.pixelboard.canvas.Layer

@Composable
fun LayerBar(
    modifier: Modifier = Modifier,
    layers: List<Layer>,
    currentLayerId: Int,

    onLayerSelect: (Int) -> Unit = {},
    onLayerVisibilityToggle: (Int) -> Unit = {},

    /// Used for re-rendering the component when needed
    @Suppress("UNUSED_PARAMETER") rerender: Boolean = false,
) {
    ElevatedCard(
        modifier = modifier,
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(10.dp),
        ) {
            itemsIndexed(layers) { index, layer ->
                LayerCard(
                    layer = layer,
                    isCurrent = currentLayerId == index,

                    onSelect = {
                        onLayerSelect(index)
                    },
                    onToggleVisibility = {
                        onLayerVisibilityToggle(index)
                    },
                )
            }
        }
    }
}

@Composable
private fun LayerCard(
    layer: Layer,
    isCurrent: Boolean,

    onSelect: () -> Unit,
    onToggleVisibility: () -> Unit,
) {
    val color =
        if (isCurrent) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary
    val onColor =
        if (isCurrent) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.onPrimary

    Button(
        modifier = Modifier
            .width(200.dp),

        onClick = onSelect,
        colors = ButtonDefaults.buttonColors(color, onColor),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier
                    .width(IntrinsicSize.Max),

                text = layer.name,
                color = onColor,
            )

            val visibilityIcon = if (layer.visible) Icons.Default.Share else Icons.Default.List
            Icon(
                modifier = Modifier
                    .clickable(onClick = onToggleVisibility),

                imageVector = visibilityIcon,
                contentDescription = null,

                tint = onColor,
            )
        }
    }
}