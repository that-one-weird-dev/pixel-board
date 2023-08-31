package com.zeropixel.pixelboard.canvas.actions

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.zeropixel.pixelboard.R
import com.zeropixel.pixelboard.canvas.serialization.saveCanvas
import com.zeropixel.pixelboard.views.CanvasViewModel

class SaveAction : Action {

    @Composable
    override fun icon(): ImageVector {
        return ImageVector.vectorResource(R.drawable.save)
    }

    override fun CanvasViewModel.execute() {
        saveCanvas(filesDir, "default", canvas)
    }
}