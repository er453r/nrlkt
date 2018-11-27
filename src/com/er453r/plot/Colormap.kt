package com.er453r.plot

import kotlin.math.floor
import kotlin.math.round

open class Colormap {
	private var lut:Array<Array<Float>>
	private var cache:MutableList<Color>
	private var buckets:Int

	constructor(lut:Array<Array<Float>>, buckets:Int = 256){
		this.lut = lut
		this.buckets = buckets
		this.cache = mutableListOf()

		var step:Float = 1f / buckets

		for(n in 0..buckets + 1)
			cache.add(getColorFromLut(n * step))
	}

	fun getColorFromLut(value:Float):Color{
		var value:Float = if (value < 0) 0f else (if(value > 1) 1f else value)

		var thisIndex:Int = floor((lut.size - 1) * value).toInt()
		var nextIndex:Int = if (thisIndex + 1 == lut.size) thisIndex else thisIndex + 1

		var thisValue:Float = thisIndex.toFloat() / (lut.size - 1)
		var nextValue:Float = nextIndex.toFloat() / (lut.size - 1)

		var thisWeight:Float = 1 - (value - thisValue)
		var nextWeight:Float = 1 - thisWeight

		var thisColor:Array<Float> = lut[thisIndex]
		var nextColor:Array<Float> = lut[nextIndex]

		var r:Int = round(255 * (thisColor[0] * thisWeight + nextColor[0] * nextWeight)).toInt()
		var g:Int = round(255 * (thisColor[1] * thisWeight + nextColor[1] * nextWeight)).toInt()
		var b:Int = round(255 * (thisColor[2] * thisWeight + nextColor[2] * nextWeight)).toInt()

		return Color(r, g, b)
	}

	fun getColor(value:Float):Color{
		var value:Float = if(value < 0)  0f else (if(value > 1) 1f else value)

		return cache[round(value * buckets.toFloat()).toInt()]
	}
}
