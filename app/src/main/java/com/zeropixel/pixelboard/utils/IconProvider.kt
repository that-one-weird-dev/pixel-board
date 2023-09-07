package com.zeropixel.pixelboard.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

interface IconProvider {
    val icon: @Composable () -> ImageVector
}