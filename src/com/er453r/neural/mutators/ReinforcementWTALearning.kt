package com.er453r.neural.mutators

import com.er453r.neural.Neuron
import com.er453r.neural.NeuronMutator
import com.er453r.neural.Synapse

class ReinforcementWTALearning : NeuronMutator(){
	override fun onStep(neuron:Neuron){
		// weight modificaiton propagation
		var max:Float = 0f
		var maxInput:Synapse = neuron.inputs[0]

		for(input in neuron.inputs){
			var value:Float = input.getValue()

			if(max < value){
				max = value
				maxInput = input
			}
		}

		maxInput.weight += neuron.learning * neuron.value * 1000000
		maxInput.weight = if (maxInput.weight > 1)  1f else maxInput.weight

		neuron.learn = maxInput.input.learning * maxInput.weight
	}
}
