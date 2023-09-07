package com.zeropixel.engine.serialization

import com.zeropixel.engine.Engine
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

fun saveEngine(parent: String, fileName: String, engine: Engine) {
    saveData(parent, fileName, serializeEngine(engine))
}

fun loadEngine(parent: String, fileName: String): Engine? {
    return deserializeEngine(loadData(parent, fileName) ?: return null)
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

fun serializeEngine(engine: Engine): String {
    val serializableCanvas = SerializedEngine.from(engine)

    return Json.encodeToString(serializableCanvas)
}

fun deserializeEngine(data: String): Engine? {
    return try {
        val serializedData = Json.decodeFromString<SerializedEngine>(data)
        serializedData.deserialize()
    } catch (ex: Exception) {
        null
    }
}