package com.zeropixel.engine.tools

interface Tool {
    fun drawStart(x: Int, y: Int) {}
    fun draw(x: Int, y: Int) {}
    fun drawEnd() {}
}