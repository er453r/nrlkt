package com.er453r.neural.mutators

import com.er453r.neural.Neuron
import com.er453r.neural.NeuronMutator
import kotlin.math.exp

class Sigmoid : NeuronMutator(){
	override fun onStep(neuron:Neuron){
		var fired:Float = 0f

		for(input in neuron.inputs)
			fired += input.getValue()

		neuron.fired = sigmoid(fired)
	}

	fun sigmoid(x:Float, beta:Float = 1f):Float{
		return (2 / (1 + exp(-(beta * x)))) - 1
	}
}
