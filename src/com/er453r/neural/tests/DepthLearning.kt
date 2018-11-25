package com.er453r.neural.tests

import com.er453r.neural.Neuron
import com.er453r.neural.NeuronMutator
import com.er453r.neural.Synapse

class DepthLearning : NeuronMutator(){
	fun new(){}

	override fun onStep(neuron:Neuron){
		// creates gradient for the singal to propagate into
		var max:Float = neuron.outputs[0].output.learning * neuron.outputs[0].weight
		var maxLearner:Synapse = neuron.outputs[0]
		var found:Boolean = false

		for(output in neuron.outputs){
			var value:Float = output.output.learning * output.weight

			output.weight *= 0.9f

			if(max < value){
				max = value
				maxLearner = output
				found = true
			}
		}

		neuron.learn = maxLearner.output.learning * maxLearner.weight

		// makes the signal follow the steepest slope
		if(found){
			maxLearner.weight = maxLearner.weight * 0.9f + 0.1f
		}
	}
}
