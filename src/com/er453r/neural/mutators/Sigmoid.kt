package com.er453r.neural.mutators

import com.er453r.neural.Neuron
import com.er453r.neural.NeuronMutator
import kotlin.math.exp

class Sigmoid : NeuronMutator {
    override fun onStep(neuron: Neuron) {
        var fired = 0f

        neuron.inputs.forEach { fired += it.getValue() }

        neuron.fired = sigmoid(fired)
    }

    fun sigmoid(x: Float, beta: Float = 1f): Float {
        return (2 / (1 + exp(-(beta * x)))) - 1
    }
}
