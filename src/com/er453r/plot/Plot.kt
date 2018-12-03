package com.er453r.plot

import org.w3c.dom.svg.SVGElement
import org.w3c.dom.svg.SVGPathElement
import org.w3c.dom.svg.SVGTextElement
import kotlin.browser.document

class Plot(private val width: Int, private val height: Int, selector: String = "body") {
	private val path:SVGPathElement

	private val min:SVGTextElement
	private val max:SVGTextElement

	init {
		val svg = document.createElementNS("http://www.w3.org/2000/svg", "svg") as SVGElement

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

	fun floats(data:FloatArray) {
		val min = PlotUtils.min(data)
		val max = PlotUtils.max(data)

		val horizontalScale = width.toFloat() / data.size
		val verticalScale = 1 / (max - min)

		var pathString = ""

		for(n in 0 until data.size){
			val value = data[n]

			val x = n * horizontalScale
			val y = height * (1 - ((value - min) * verticalScale))

			if(y.isNaN())
				continue

			if(n == 0)
				pathString += "M $x $y"
			else
				pathString += " L $x $y"
		}

		this.min.innerHTML = "$min"
		this.max.innerHTML = "$max"

		path.setAttribute("d", pathString)
	}
}
