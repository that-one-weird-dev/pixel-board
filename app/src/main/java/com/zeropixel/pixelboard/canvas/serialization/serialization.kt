package com.zeropixel.pixelboard.canvas.serialization

import com.zeropixel.pixelboard.canvas.Canvas
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

fun saveCanvas(parent: String, fileName: String, canvas: Canvas) {
    saveData(parent, fileName, serializeCanvas(canvas))
}

fun loadCanvas(parent: String, fileName: String): Canvas? {
    return deserializeCanvas(loadData(parent, fileName) ?: return null)
}

fun saveData(parent: String, fileName: String, data: String) {
    File(parent, fileName)
        .writeText(data)
}

fun loadData(parent: String, fileName: String): String? {
    return try {
        File(parent, fileName)
            .readText()
    } catch (ex: Exception) {
        null
    }
}

fun serializeCanvas(canvas: Canvas): String {
    val serializableCanvas = SerializedCanvas.from(canvas)

    return Json.encodeToString(serializableCanvas)
}

fun deserializeCanvas(data: String): Canvas? {
    return try {
        val serializedData = Json.decodeFromString<SerializedCanvas>(data)
        serializedData.deserialize()
    } catch (ex: Exception) {
        null
    }
}