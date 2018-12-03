package com.er453r.neural.tests

import kotlin.math.log10

class LogScale(private val min: Float = 1e-16f, private val max: Float = 1f) {
    private val scalar: Float = 1 / (log10(max) - log10(min))
    private val offset: Float

    init {
        this.offset = -log10(min) * scalar
    }

    fun scale(value: Float): Float {
        if (value < min)
            return 0f

        if (value > max)
            return 1f

        return log10(value) * scalar + offset
    }
}
