package com.zeropixel.pixelboard.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zeropixel.pixelboard.ui.components.menus.ConfirmMenu
import com.zeropixel.pixelboard.views.CanvasViewModel

@Composable
fun ConfirmMenuScreen(canvasViewModel: CanvasViewModel) {
    canvasViewModel.confirmMenu?.let {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            ConfirmMenu(
                it.message,
                onCancel = {
                    it.onCancel()
                    canvasViewModel.confirmMenu = null
                },
                onConfirm = {
                    it.onConfirm()
                    canvasViewModel.confirmMenu = null
                }
            )
        }
    }
}