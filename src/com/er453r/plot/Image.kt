package com.er453r.plot

import org.khronos.webgl.Uint8ClampedArray
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.ImageData
import kotlin.browser.document

class Image(width: Int, height: Int, private val colormap: Colormap, selector: String = "body") {
    private var image: ImageData
    private var context: CanvasRenderingContext2D

    init {
        val canvas: HTMLCanvasElement = document.createElement("canvas") as HTMLCanvasElement
        canvas.width = width
        canvas.height = height
        context = canvas.getContext("2d")!! as CanvasRenderingContext2D
        image = context.getImageData(0.0, 0.0, width.toDouble(), height.toDouble())

        document.querySelector(selector)!!.appendChild(canvas)
    }

    fun <T> generic(vector: Array<T>, collector: (T) -> Float) {
        val data: FloatArray = collect(vector, collector)

        val min: Float = PlotUtils.min(data)
        val max: Float = PlotUtils.max(data)

        val scale: Float = if (max == min) 0f else 1f / (max - min)

        val pixels: Uint8ClampedArray = image.data

        for (n in 0 until pixels.length / 4) {
            val color: Color = colormap.getColor((data[n] - min) * scale)

            pixels[4 * n + 0] = color.r // Red value
            pixels[4 * n + 1] = color.g//color.g.toByte() // Green value
            pixels[4 * n + 2] = color.b//color.b.toByte() // Blue value
            pixels[4 * n + 3] = 255//255.toByte() // Alpha value
        }

        context.putImageData(image, 0.0, 0.0)
    }

    private fun <T> collect(data: Array<T>, collector: (T) -> Float): FloatArray {
        return FloatArray(data.size) { collector(data[it]) }
    }
}

inline operator fun Uint8ClampedArray.set(index: Int, value: Int) {
    asDynamic()[index] = value; }
