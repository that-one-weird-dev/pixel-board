package com.zeropixel.engine.utils

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.math.abs
import kotlin.math.round
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

    for (xOffset in 0 until width) {
        for (yOffset in 0 until height) {
            block(x + xOffset, y + yOffset)
        }
    }
}

@OptIn(ExperimentalContracts::class)
inline fun linePixels(
    xStart: Int,
    yStart: Int,
    xEnd: Int,
    yEnd: Int,
    block: (xPos: Int, yPos: Int) -> Unit
) {
    contract {
        callsInPlace(block, InvocationKind.UNKNOWN)
    }

    val xDiff = xEnd - xStart
    val yDiff = yEnd - yStart

    val steps = if (abs(xDiff) > abs(yDiff)) abs(xDiff) else abs(yDiff)

    val xInc = xDiff / steps.toFloat()
    val yInc = yDiff / steps.toFloat()

    var x: Float = xStart.toFloat()
    var y: Float = yStart.toFloat()
    for (i in 0..steps) {
        block(round(x).toInt(), round(y).toInt())

        x += xInc
        y += yInc
    }
}