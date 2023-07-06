package com.zeropixel.pixelboard.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CanvasViewModelFactory(
    private val width: Int,
    private val height: Int,
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CanvasViewModel(width, height) as T
    }
}