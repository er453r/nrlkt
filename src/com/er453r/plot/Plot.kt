package com.er453r.plot

import org.w3c.dom.svg.SVGElement
import org.w3c.dom.svg.SVGPathElement
import org.w3c.dom.svg.SVGTextElement
import kotlin.browser.document

class Plot {
	companion object {
	    val MARGIN:Float = 0.2f
	}

	private var path:SVGPathElement

	private var width:Int
	private var height:Int

	private var min:SVGTextElement
	private var max:SVGTextElement

	constructor(width:Int, height:Int, selector:String = "body") {
		this.width = width
		this.height = height

		var svg:SVGElement = document.createElementNS("http://www.w3.org/2000/svg", "svg") as SVGElement

		svg.setAttribute("width", width.toString())
		svg.setAttribute("height", height.toString())

		path = document.createElementNS("http://www.w3.org/2000/svg", "path") as SVGPathElement

		svg.appendChild(path)

		path.setAttribute("stroke", "#000000")
		path.setAttribute("fill-opacity", "0")

		path.setAttribute("d", "M 0 0 L 100 100")

		min = document.createElementNS("http://www.w3.org/2000/svg", "text") as SVGTextElement
		min.innerHTML = "min"
		min.setAttribute("x", "10")
		min.setAttribute("y", "${height-30}")

		svg.appendChild(min)

		max = document.createElementNS("http://www.w3.org/2000/svg", "text") as SVGTextElement
		max.innerHTML = "max"
		max.setAttribute("x", "10")
		max.setAttribute("y", "10")

		svg.appendChild(max)

		document.querySelector(selector)!!.appendChild(svg)
	}

	fun floats(data:List<Float>) {
		var min:Float = PlotUtils.min(data)
		var max:Float = PlotUtils.max(data)

		var horizontalScale:Float = width.toFloat() / data.size
		var verticalScale:Float = 1 / (max - min)

		var pathString:String = ""

		for(n in 0..data.size){
			var value:Float = data[n]

			var x:Float = n * horizontalScale
			var y:Float = height * (1 - ((value - min) * verticalScale))

			if(y.isNaN())
				continue

			if(n == 0)
				pathString += " ${x} ${y}"
			else
				pathString += " L ${x} ${y}"
		}

		this.min.innerHTML = "${min}"
		this.max.innerHTML = "${max}"

		path.setAttribute("d", pathString)
	}
}
