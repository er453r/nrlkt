package com.er453r.neural.mutators

import com.er453r.neural.NeuronMutator
import com.er453r.neural.Synapse
import kotlin.random.Random

class SymmetricWeights : NeuronMutator(){
	override fun onSynapse(synapse:Synapse){
		synapse.weight = 2 * Random.nextFloat() - 1
	}
}
