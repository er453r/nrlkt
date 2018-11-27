package com.er453r.neural

class Neuron {
	var inputs:MutableList<Synapse> = mutableListOf()
	var outputs:MutableList<Synapse> = mutableListOf()

	private var mutators:MutableList<NeuronMutator> = mutableListOf()

	var value:Float = 0f
	var fired:Float = 0f

	var learn:Float = 0f
	var learning:Float = 0f

	constructor(mutators:MutableList<NeuronMutator>) {
		this.mutators = mutators

		for(mutator in mutators)
			mutator.onInit(this)
	}

	fun addInput(neuron:Neuron){
		var synapse:Synapse = Synapse(neuron, this)

		for(mutator in mutators)
			mutator.onSynapse(synapse)

		inputs.add(synapse)
	}

	fun step(){
		for(mutator in mutators)
			mutator.onStep(this)
	}
	
	fun propagate(){
		value = fired
		learning = learn
	}
}
