package com.er453r.neural.tests

import kotlin.js.Date

class FPS {
	private var frames:Int = 0
	private var fps:Int = 0

	private var past:Float = Date.now().toFloat()

	fun update():Int{
		frames++

		var now:Float = Date.now().toFloat()

		var diff:Float = (now - past) / 1e3f

		if(diff > 1){
			fps = (frames.toFloat() / diff).toInt()

			past = now
			frames = 0
		}

		return fps
	}
}
