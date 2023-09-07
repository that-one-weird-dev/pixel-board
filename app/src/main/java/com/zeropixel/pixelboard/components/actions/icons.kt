package com.zeropixel.pixelboard.components.actions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

typealias IconFunc = @Composable () -> ImageVector

val clearActionIcon: IconFunc = @Composable { Icons.Rounded.Clear }
val undoActionIcon: IconFunc = @Composable { Icons.Rounded.ArrowBack }
