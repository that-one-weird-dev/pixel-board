package com.zeropixel.pixelboard.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.zeropixel.pixelboard.ui.components.PixelCanvas
import com.zeropixel.pixelboard.ui.components.quickbar.QuickBar
import com.zeropixel.pixelboard.ui.theme.Colors
import com.zeropixel.pixelboard.views.CanvasViewModel

@Composable
fun CanvasScreen(viewModel: CanvasViewModel) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.Dark1),

        contentAlignment = Alignment.Center,
    ) {
        PixelCanvas(
            viewModel.imageBitmap,
            onPixelDraw = viewModel::useAt,
        )
        Box(
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            QuickBar(
                currentColor = viewModel.currentColor,
                colorPalette = viewModel.colorPalette,
                onColorPick = { viewModel.currentColor = it },

                currentTool = viewModel.currentTool,
                toolPalette = viewModel.toolPalette,
                onToolPick = { viewModel.currentTool = it },

                actionPalette = viewModel.actionPalette,
                onActionPick = { viewModel.executeAction(it) },
            )
        }
    }
}
