package com.zeropixel.pixelboard.components.actionbar

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zeropixel.pixelboard.components.IconPalette
import com.zeropixel.pixelboard.components.shared.Panel
import com.zeropixel.pixelboard.utils.ComposableAction

@Composable
fun ActionBar(
    modifier: Modifier = Modifier,
    actionPalette: List<ComposableAction<*>>,
    onActionPick: (ComposableAction<*>) -> Unit,
) {
    Panel(
        modifier = modifier,
    ) {
        IconPalette(
            modifier = Modifier.padding(10.dp),

            palette = actionPalette,
            onSelect = onActionPick,
            isSelected = { false }
        )
    }
}