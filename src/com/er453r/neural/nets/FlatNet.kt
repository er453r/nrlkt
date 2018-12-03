package com.er453r.neural.nets

import com.er453r.neural.Neuron
import kotlin.js.Date

class FlatNet(width: Int, height: Int, d: Int = 1, getNeuron: () -> Neuron) : Network {
    private val neurons: Array<Neuron>

    init {
        var past: Float = Date.now().toFloat()

        neurons = Array(width * height) { getNeuron() }

        neurons.forEachIndexed { n, neuron ->
            val x: Int = n % width
            val y: Int = n / width

            val startX: Int = if (x - d < 0) 0 else x - d
            val startY: Int = if (y - d < 0) 0 else y - d

            val endX: Int = if (x + d + 1 > width) width else x + d + 1
            val endY: Int = if (y + d + 1 > height) height else y + d + 1

            for (y_ in startY until endY)
                for (x_ in startX until endX)
                    if (y_ != y || x_ != x)
                        neuron.addInput(neurons[y_ * width + x_])
        }

        val forwardTime: Float = Date.now().toFloat() - past

        past = Date.now().toFloat()

        neurons.forEach { neuron ->
            for (neighbour in neuron.inputs)
                for (neighbourInput in neighbour.input.inputs)
                    if (neighbourInput.input == neuron)
                        neuron.outputs.add(neighbourInput)
        }

        val reverseTime: Float = Date.now().toFloat() - past

        println("Created forward in $forwardTime ms., reverse in $reverseTime ms.'")
    }

    override fun getNeurons() = neurons

    override fun update() {
        neurons.forEach { it.step() }
        neurons.forEach { it.propagate() }
    }
}
