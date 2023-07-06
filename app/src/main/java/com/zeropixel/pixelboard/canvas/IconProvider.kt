package com.zeropixel.pixelboard.canvas

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

interface IconProvider {
    @Composable
    fun icon(): ImageVector
}