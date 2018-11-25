package com.er453r.neural

open class NeuronMutator {
	open fun onInit(neuron:Neuron){}
	open fun onStep(neuron:Neuron){}
	open fun onSynapse(synapse:Synapse){}
}
