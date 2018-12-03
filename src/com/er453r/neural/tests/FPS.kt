package com.er453r.neural.tests

import kotlin.js.Date

class FPS {
    private var frames: Int = 0
    private var fps: Int = 0

    private var past: Float = Date.now().toFloat()

    fun update(): Int {
        frames++

        val now: Float = Date.now().toFloat()

        val diff: Float = (now - past) / 1e3f

        if (diff > 1) {
            fps = (frames.toFloat() / diff).toInt()

            past = now
            frames = 0
        }

        return fps
    }
}
