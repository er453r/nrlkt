package com.er453r.neural.mutators

import com.er453r.neural.Neuron
import com.er453r.neural.NeuronMutator

class Decay(private var treshold: Float = 0.00001f, private var decay: Float = 0.9f) : NeuronMutator() {

	override fun onStep(neuron:Neuron){
		if(neuron.value > treshold)
			neuron.fired = neuron.value * decay
	}
}
