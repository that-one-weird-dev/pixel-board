package com.zeropixel.engine.undo

interface Undoable {
    val name: String

    fun undo()
}