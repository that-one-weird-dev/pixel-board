package com.zeropixel.pixelboard.components.toolbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun ToolConfigSlider(
    minValue: Int = 0,
    maxValue: Int = 10,
    defaultValue: Int = minValue,
    onValueChange: (Int) -> Unit = {},
    content: @Composable () -> Unit,
) {
    var sliderValue by remember { mutableStateOf(defaultValue.toFloat()) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        content()

        Slider(
            steps = maxValue - minValue,
            value = sliderValue,
            valueRange = minValue.toFloat()..maxValue.toFloat(),
            onValueChange = {
                sliderValue = it
                onValueChange(it.roundToInt())
            },
        )
    }
}