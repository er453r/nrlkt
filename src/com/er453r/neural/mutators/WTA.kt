package com.er453r.neural.mutators

import com.er453r.neural.Neuron
import com.er453r.neural.NeuronMutator

class WTA : NeuronMutator(){
	override fun onStep(neuron:Neuron){
		var max:Float = 0f

		for(input in neuron.inputs){
			var value:Float = input.getValue()

			if(max < value)
				max = value
		}

		neuron.fired = max
	}
}