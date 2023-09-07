package com.zeropixel.pixelboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zeropixel.engine.Canvas
import com.zeropixel.engine.Engine
import com.zeropixel.engine.palette.ColorPalette
import com.zeropixel.engine.serialization.loadEngine
import com.zeropixel.pixelboard.screens.CanvasScreen
import com.zeropixel.pixelboard.utils.fromBitmap
import com.zeropixel.pixelboard.views.CanvasViewModel
import com.zeropixel.pixelboard.views.CanvasViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val colorScheme = dynamicDarkColorScheme(LocalContext.current)

            MaterialTheme(colorScheme = colorScheme) {
                val filesDir = LocalContext.current.filesDir?.parent ?: ""
                val engine = loadEngine(filesDir, "default") ?: defaultEngine()

                val canvasViewModel =
                    viewModel<CanvasViewModel>(
                        factory = CanvasViewModelFactory(
                            engine,
                            filesDir
                        )
                    )

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    CanvasScreen(canvasViewModel)
                }
            }
        }
    }

    @Composable
    fun defaultEngine(): Engine {
        val image = ImageBitmap.imageResource(id = R.drawable.apollo)

        return Engine(
            Canvas(32, 32),
            ColorPalette.fromBitmap(image),
        )
    }
}