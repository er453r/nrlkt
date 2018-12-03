package com.er453r.neural.mutators

import com.er453r.neural.Neuron
import com.er453r.neural.NeuronMutator

class WTA : NeuronMutator {
    override fun onStep(neuron: Neuron) {
        var max = 0f

        neuron.inputs.forEach {
            val value: Float = it.getValue()

            if (max < value)
                max = value
        }

        neuron.fired = max
    }
}
