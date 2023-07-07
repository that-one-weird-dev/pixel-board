package com.zeropixel.pixelboard.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zeropixel.pixelboard.ui.components.PixelCanvas
import com.zeropixel.pixelboard.ui.components.quickbar.QuickBar
import com.zeropixel.pixelboard.views.CanvasViewModel

@Composable
fun CanvasScreen(viewModel: CanvasViewModel) {

    PixelCanvas(
        viewModel.imageBitmap,
        onPixelDraw = viewModel::useAt,
    )

    Box(Modifier.padding(10.dp)) {
        QuickBar(
            modifier = Modifier.align(Alignment.CenterStart),

            currentColor = viewModel.currentColor,
            colorPalette = viewModel.colorPalette,
            onColorPick = { viewModel.currentColor = it },

            currentTool = viewModel.currentTool,
            toolPalette = viewModel.toolPalette,
            onToolPick = { viewModel.currentTool = it },

            actionPalette = viewModel.actionPalette,
            onActionPick = { viewModel.executeAction(it) },

            columns = 1,
        )
    }
}
