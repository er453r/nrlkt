package com.er453r.neural.tests

import kotlin.math.log10

class LogScale {
	private var min:Float
	private var max:Float

	private var scalar:Float
	private var offset:Float

	constructor(min:Float = 1e-16f, max:Float = 1f) {
		this.min = min
		this.max = max

		this.scalar = 1 / (log10(max) - log10(min))
		this.offset = -log10(min) * scalar
	}

	fun scale(value:Float):Float{
		if(value < min)
			return 0f

		if(value > max)
			return 1f

		return log10(value) * scalar + offset
	}
}
