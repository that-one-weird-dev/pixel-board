package com.zeropixel.pixelboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zeropixel.pixelboard.ui.screens.CanvasScreen
import com.zeropixel.pixelboard.ui.screens.ConfirmMenuScreen
import com.zeropixel.pixelboard.views.CanvasViewModel
import com.zeropixel.pixelboard.views.CanvasViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val canvasViewModel =
                viewModel<CanvasViewModel>(factory = CanvasViewModelFactory(32, 32))

            CanvasScreen(canvasViewModel)
            ConfirmMenuScreen(canvasViewModel)
        }
    }
}