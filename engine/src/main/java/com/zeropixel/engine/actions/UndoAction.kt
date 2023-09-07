package com.zeropixel.engine.actions

import com.zeropixel.engine.Engine

class UndoAction(
    val engine: Engine,
) : Action {

    override fun execute() {
        engine.popUndoableAndUndo()
    }
}