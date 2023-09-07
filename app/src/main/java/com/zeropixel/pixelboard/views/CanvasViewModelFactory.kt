package com.zeropixel.pixelboard.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zeropixel.engine.Engine

class CanvasViewModelFactory(
    private val engine: Engine,
    private val filesDir: String,
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return CanvasViewModel(engine, filesDir) as T
    }
}