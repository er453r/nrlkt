package com.er453r.neural.mutators

import com.er453r.neural.NeuronMutator
import com.er453r.neural.Synapse
import kotlin.random.Random

class PositiveWeights(private var scale: Float = 1.0f) : NeuronMutator {
    override fun onSynapse(synapse: Synapse) {
        synapse.weight = scale * Random.nextFloat()
    }
}
