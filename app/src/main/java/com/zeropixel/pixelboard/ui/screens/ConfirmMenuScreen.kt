package com.zeropixel.pixelboard.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.zeropixel.pixelboard.ui.components.menus.ConfirmMenu
import com.zeropixel.pixelboard.views.CanvasViewModel

@Composable
fun ConfirmMenuScreen(canvasViewModel: CanvasViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.Center),

            visible = canvasViewModel.confirmMenu != null,
        ) {
            val message = canvasViewModel.confirmMenu?.message ?: ""

            ConfirmMenu(
                message,
                onCancel = {
                    canvasViewModel.confirmMenu!!.onCancel()
                    canvasViewModel.confirmMenu = null
                },
                onConfirm = {
                    canvasViewModel.confirmMenu!!.onConfirm()
                    canvasViewModel.confirmMenu = null
                }
            )
        }
    }
}