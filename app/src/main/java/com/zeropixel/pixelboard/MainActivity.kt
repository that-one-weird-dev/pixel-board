package com.zeropixel.pixelboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zeropixel.pixelboard.ui.screens.CanvasScreen
import com.zeropixel.pixelboard.ui.screens.ConfirmMenuScreen
import com.zeropixel.pixelboard.views.CanvasViewModel
import com.zeropixel.pixelboard.views.CanvasViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val colorScheme = dynamicDarkColorScheme(LocalContext.current)

            MaterialTheme(colorScheme = colorScheme) {
                val canvasViewModel =
                    viewModel<CanvasViewModel>(factory = CanvasViewModelFactory(32, 32))

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    CanvasScreen(canvasViewModel)
                    ConfirmMenuScreen(canvasViewModel)
                }
            }
        }
    }
}