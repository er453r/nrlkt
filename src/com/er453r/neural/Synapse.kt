package com.er453r.neural

class Synapse(var input: Neuron, var output: Neuron, var weight: Float = 0f) {
	var dw:Float = 0.0f
	var learn:Float = 0.0f
	var dl:Float = 0f

	fun getValue():Float{
		return input.value * weight
	}
}
