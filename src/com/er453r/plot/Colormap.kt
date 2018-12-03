package com.er453r.plot

import kotlin.math.floor
import kotlin.math.round

open class Colormap(private val lut: Array<Array<Float>>, private val buckets: Int = 256) {
    private val cache: Array<Color>

    init {
        val step: Float = 1f / buckets

        cache = Array(buckets + 1) { n -> getColorFromLut(n * step) }
    }

    private fun getColorFromLut(realValue: Float): Color {
        val value: Float = if (realValue < 0) 0f else (if (realValue > 1) 1f else realValue)

        val thisIndex: Int = floor((lut.size - 1) * value).toInt()
        val nextIndex: Int = if (thisIndex + 1 == lut.size) thisIndex else thisIndex + 1

        val thisValue: Float = thisIndex.toFloat() / (lut.size - 1)
//        var nextValue: Float = nextIndex.toFloat() / (lut.size - 1)

        val thisWeight: Float = 1 - (value - thisValue)
        val nextWeight: Float = 1 - thisWeight

        val thisColor: Array<Float> = lut[thisIndex]
        val nextColor: Array<Float> = lut[nextIndex]

        val r: Int = round(255 * (thisColor[0] * thisWeight + nextColor[0] * nextWeight)).toInt()
        val g: Int = round(255 * (thisColor[1] * thisWeight + nextColor[1] * nextWeight)).toInt()
        val b: Int = round(255 * (thisColor[2] * thisWeight + nextColor[2] * nextWeight)).toInt()

        return Color(r, g, b)
    }

    fun getColor(realValue: Float): Color {
        val value: Float = if (realValue < 0) 0f else (if (realValue > 1) 1f else realValue)

        return cache[round(value * buckets.toFloat()).toInt()]
    }
}
