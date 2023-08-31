package com.zeropixel.pixelboard.canvas.utils

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.math.sqrt

const val CIRCLE_RADIUS_ERROR = .1

@OptIn(ExperimentalContracts::class)
inline fun circlePixels(x: Int, y: Int, radius: Float, block: (xPos: Int, yPos: Int) -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.UNKNOWN)
    }

    for (xOffset in (-radius.toInt())..radius.toInt()) {
        for (yOffset in (-radius.toInt())..radius.toInt()) {
            val distance = sqrt((xOffset * xOffset + yOffset * yOffset).toFloat())

            if (distance < radius + CIRCLE_RADIUS_ERROR) {
                block(x + xOffset, y + yOffset)
            }
        }
    }
}

@OptIn(ExperimentalContracts::class)
inline fun rectanglePixels(
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    block: (xPos: Int, yPos: Int) -> Unit
) {
    contract {
        callsInPlace(block, InvocationKind.UNKNOWN)
    }

    for (xOffset in 0..width) {
        for (yOffset in 0..height) {
            block(x + xOffset, y + yOffset)
        }
    }
}