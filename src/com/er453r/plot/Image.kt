package com.er453r.plot

import com.er453r.plot.colormaps.Cold
import org.khronos.webgl.Uint8ClampedArray
import org.khronos.webgl.set
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.ImageData
import kotlin.browser.document

class Image {
	private var image:ImageData
	private var context:CanvasRenderingContext2D
	private var colormap:Colormap?

	constructor(width:Int, height:Int, colormap:Colormap? = null, selector:String = "body") {
		this.colormap = colormap

		var canvas:HTMLCanvasElement = document.createElement("canvas") as HTMLCanvasElement
		canvas.width = width
		canvas.height = height

		context = canvas.getContext("2d")!! as CanvasRenderingContext2D
		image = context.getImageData(0.0,0.0, width.toDouble(), height.toDouble())

		if(this.colormap == null)
			this.colormap = Cold()

		document.querySelector(selector)?.let { it.appendChild(canvas) }
	}

	fun <T> generic(vector:MutableList<T>, collector:(T)->Float) {
		var data:List<Float> = collect(vector, collector)

		var min:Float = PlotUtils.min(data)
		var max:Float = PlotUtils.max(data)

		var scale:Float = if (max == min) 0f else 1f / (max - min)

		var pixels:Uint8ClampedArray = image.data

		for(n in 0..pixels.length/4){
			var color:Color = colormap!!.getColor((data[n] - min) * scale)

			pixels[4 * n + 0] = color.r.toByte() // Red value
			pixels[4 * n + 1] = color.g.toByte() // Green value
			pixels[4 * n + 2] = color.b.toByte() // Blue value
			pixels[4 * n + 3] = 255.toByte() // Alpha value
		}

		context.putImageData(image, 0.0, 0.0)
	}

	private  fun <T> collect(data:MutableList<T>, collector:(T)->Float):List<Float>{
		return data.map { collector(it) }
	}
}
