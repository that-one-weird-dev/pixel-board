package com.zeropixel.pixelboard.ui.components.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zeropixel.pixelboard.ui.components.utils.advancedShadow

@Composable
inline fun Panel(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .border(2.dp, Color.Black)
            .advancedShadow(Color.Black, 8.dp, 8.dp)
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)),
        content = content,
    )
}