package com.er453r.neural.mutators

import com.er453r.neural.Neuron
import com.er453r.neural.NeuronMutator

class Decay(private val threshold: Float = 0.00001f, private val decay: Float = 0.9f) : NeuronMutator {
    override fun onStep(neuron: Neuron) {
        if (neuron.value > threshold)
            neuron.fired = neuron.value * decay
    }
}
