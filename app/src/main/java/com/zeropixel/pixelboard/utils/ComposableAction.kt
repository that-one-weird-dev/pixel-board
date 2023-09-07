package com.zeropixel.pixelboard.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.zeropixel.engine.actions.Action

class ComposableAction<T : Action>(
    val action: T,
    override val icon: @Composable () -> ImageVector,
) : IconProvider