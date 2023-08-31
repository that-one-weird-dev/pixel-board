package com.zeropixel.pixelboard.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zeropixel.pixelboard.canvas.palette.ColorPalette

class CanvasViewModelFactory(
    private val width: Int,
    private val height: Int,
    private val palette: ColorPalette = ColorPalette(),
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return CanvasViewModel(width, height, palette) as T
    }
}