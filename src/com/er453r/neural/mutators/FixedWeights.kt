package com.er453r.neural.mutators

import com.er453r.neural.NeuronMutator
import com.er453r.neural.Synapse

class FixedWeights(private var value: Float = 1.0f) : NeuronMutator() {
	override fun onSynapse(synapse:Synapse){
		synapse.weight = value
	}
}
