package com.er453r.neural.tests

import com.er453r.neural.Neuron
import com.er453r.neural.NeuronMutator

class DepthLearning : NeuronMutator {
    override fun onStep(neuron: Neuron) {
        // creates gradient for the singal to propagate into
        var max = neuron.outputs[0].output.learning * neuron.outputs[0].weight
        var maxLearner = neuron.outputs[0]
        var found = false

        neuron.outputs.forEach { output ->
            val value: Float = output.output.learning * output.weight

            output.weight *= 0.9f

            if (max < value) {
                max = value
                maxLearner = output
                found = true
            }
        }

        neuron.learn = maxLearner.output.learning * maxLearner.weight

        // makes the signal follow the steepest slope
        if (found) {
            maxLearner.weight = maxLearner.weight * 0.9f + 0.1f
        }
    }
}
