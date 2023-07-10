package com.zeropixel.pixelboard.ui.components.actionbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zeropixel.pixelboard.canvas.actions.Action
import com.zeropixel.pixelboard.ui.components.IconPalette

@Composable
fun ActionBar(
    modifier: Modifier = Modifier,
    actionPalette: List<Action>,
    onActionPick: (Action) -> Unit,
) {
    ElevatedCard(
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