package com.er453r.neural.mutators

import com.er453r.neural.Neuron
import com.er453r.neural.NeuronMutator
import com.er453r.neural.Synapse

class LearningWTA(private var treshold: Float = 0.000001f) : NeuronMutator() {
	private var momentum:Float = 0.9f
	var dl:Float = 0f

	override fun onStep(neuron: Neuron){
		// weight modificaiton propagation
		var max:Float = 0f
		var maxSynapse: Synapse = neuron.inputs[0]

		// we find the input with the biggest value
		for(input in neuron.inputs){
			var value:Float = input.getValue()

			if(max < value){
				max = value
				maxSynapse = input
			}
		}

		// we increase the weighta when:
		// the neuron is learning AND
		// the neuron is active
		maxSynapse.dw = momentum * maxSynapse.dw + neuron.learning * neuron.value
		//maxSynapse.weight += maxSynapse.dw
	//	maxSynapse.weight = maxSynapse.weight > 1 ? 1 : maxSynapse.weight
	//	maxSynapse.weight *= 0.9998

		// learing signal propagation
		max = 0f
		maxSynapse = neuron.outputs[0]

		// we find the input withe biggest share
		for(input in neuron.inputs){
			var value:Float = input.input.value

			if(max < value){
				max = value
				maxSynapse = input
			}
		}

		maxSynapse.learn = 1 * max

		// learing signal propagation
		max = 0f
		maxSynapse = neuron.outputs[0]

		// we find the output withe biggest learning factor
		for(output in neuron.outputs){
			var value:Float = output.output.learning * output.learn

			if(max < value){
				max = value
				maxSynapse = output
			}
		}

		// we are only learning if:
		// we have acvity AND
		// our output is learning
		// (we do not check if output has activity,
		// since we already check for out activity)
		dl = momentum * dl + 1 * max// * neuron.value
		neuron.learn += dl
		neuron.learn = if (neuron.learn > 0) 1f else neuron.learn
	}
}
