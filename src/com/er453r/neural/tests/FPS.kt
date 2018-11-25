package com.er453r.neural.tests

import kotlin.js.Date

class FPS {
	private var frames:Int = 0
	private var fps:Int = 0

	private var past:Float = Date.now().toFloat()

	fun new(){}

	fun update():Int{
		frames++

		var now:Float = Date.now().toFloat()

		var diff:Float = now - past

		if(diff > 1){
			fps = (frames / diff).toInt()

			past = now
			frames = 0
		}

		return fps
	}
}
