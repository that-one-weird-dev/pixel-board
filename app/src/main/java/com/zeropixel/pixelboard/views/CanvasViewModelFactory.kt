package com.zeropixel.pixelboard.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zeropixel.pixelboard.canvas.Canvas

class CanvasViewModelFactory(
    private val canvas: Canvas,
    private val filesDir: String,
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return CanvasViewModel(canvas, filesDir) as T
    }
}