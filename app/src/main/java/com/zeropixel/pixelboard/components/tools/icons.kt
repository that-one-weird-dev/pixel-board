package com.zeropixel.pixelboard.components.tools

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.zeropixel.pixelboard.R

typealias IconFunc = @Composable () -> ImageVector

val penToolIcon: IconFunc = @Composable { Icons.Rounded.Edit }
val eraserToolIcon: IconFunc = @Composable { ImageVector.vectorResource(id = R.drawable.ic_eraser) }
val fillToolIcon: IconFunc = @Composable { Icons.Default.Place }
