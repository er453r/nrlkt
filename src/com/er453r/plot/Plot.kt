package com.er453r.plot

import js.html.svg.TextElement
import js.html.svg.TextContentElement
import com.er453r.plot.PlotUtils
import js.html.svg.PathElement
import js.html.svg.SVGElement
import js.Browser

class Plot {
	private static inline var MARGIN:Float = 0.2

	private var path:PathElement

	private var width:Int
	private var height:Int

	private var min:TextElement
	private var max:TextElement

	fun new(width:Int, height:Int, selector:String = "body") {
		this.width = width
		this.height = height

		var svg:SVGElement = cast Browser.document.createElementNS("http://www.w3.org/2000/svg", "svg")

		svg.setAttribute("width", Std.string(width))
		svg.setAttribute("height", Std.string(height))

		path = cast Browser.document.createElementNS("http://www.w3.org/2000/svg", "path")

		svg.appendChild(path)

		path.setAttribute("stroke", "#000000")
		path.setAttribute("fill-opacity", "0")

		path.setAttribute("d", "M 0 0 L 100 100")

		min = cast Browser.document.createElementNS("http://www.w3.org/2000/svg", "text")
		min.innerHTML = "min"
		min.setAttribute("x", "10")
		min.setAttribute("y", '${height-30}')

		svg.appendChild(min)

		max = cast Browser.document.createElementNS("http://www.w3.org/2000/svg", "text")
		max.innerHTML = "max"
		max.setAttribute("x", "10")
		max.setAttribute("y", "10")

		svg.appendChild(max)

		Browser.document.querySelector(selector).appendChild(svg)
	}

	fun floats(data:List<Float>) {
		var min:Float = PlotUtils.min(data)
		var max:Float = PlotUtils.max(data)

		var horizontalScale:Float = width / data.size
		var verticalScale:Float = 1 / (max - min)

		var pathString:String = ""

		for(n in 0..data.size){
			var value:Float = data[n]

			var x:Float = n * horizontalScale
			var y:Float = height * (1 - ((value - min) * verticalScale))

			if(Math.isNaN(y))
				continue

			if(n == 0)
				pathString += 'M ${x} ${y}'
			else
				pathString += ' L ${x} ${y}'
		}

		this.min.innerHTML = '${min}'
		this.max.innerHTML = '${max}'

		path.setAttribute("d", pathString)
	}
}
