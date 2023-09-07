package com.zeropixel.pixelboard.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zeropixel.pixelboard.components.PixelCanvas
import com.zeropixel.pixelboard.components.actionbar.ActionBar
import com.zeropixel.pixelboard.components.colorpalette.QuickBar
import com.zeropixel.pixelboard.components.layerbar.LayerBar
import com.zeropixel.pixelboard.components.toolbar.ToolBar
import com.zeropixel.pixelboard.views.CanvasViewModel

@Composable
fun CanvasScreen(viewModel: CanvasViewModel) {

    PixelCanvas(
        viewModel.engine.canvas.width,
        viewModel.engine.canvas.height,

        viewModel.engine.canvas.layers,

        onDrawStart = viewModel::startToolDraw,
        onDraw = viewModel::toolDraw,
        onDrawEnd = viewModel::endToolDraw,

        viewModel.rerenderCanvasState,
    )

    Box(Modifier.padding(10.dp)) {
        QuickBar(
            modifier = Modifier.align(Alignment.CenterStart),

            currentColor = viewModel.engine.currentColor,
            colorPalette = viewModel.engine.palette,
            onColorPick = { viewModel.currentColorId = it },

            columns = viewModel.expectedQuickBarColumns(),
        )

        ToolBar(
            modifier = Modifier.align(Alignment.TopStart),

            currentTool = viewModel.currentTool,
            toolPalette = viewModel.toolPalette,
            onToolPick = { tool -> viewModel.currentTool = tool },
        )

        ActionBar(
            modifier = Modifier.align(Alignment.BottomStart),

            actionPalette = viewModel.actionPalette,
            onActionPick = { action -> viewModel.executeAction(action) },
        )

        LayerBar(
            modifier = Modifier.align(Alignment.TopEnd),

            layers = viewModel.engine.canvas.layers,
            currentLayerId = viewModel.currentLayerId,

            onLayerSelect = { layerId ->
                viewModel.currentLayerId = layerId
            },
            onLayerVisibilityToggle = { layerId ->
                viewModel.toggleLayerVisibility(layerId)
            },

            rerender = viewModel.rerenderLayersState,
        )
    }

//    ConfirmDialog(
//        visible = viewModel.showAlertDialog,
//        options = viewModel.alertOptions,
//        onClose = { viewModel.showAlertDialog = false },
//    )
//
//    FilePicker(
//        show = viewModel.showFilePicker,
//        fileExtensions = viewModel.filePickerFileExtensions,
//    ) { file ->
//        viewModel.filePickerCallback(file)
//        viewModel.showFilePicker = false
//    }
//
//    DirectoryPicker(viewModel.showDirectoryPicker) { path ->
//        viewModel.directoryPickerCallback(path)
//        viewModel.showDirectoryPicker = false
//    }
}
