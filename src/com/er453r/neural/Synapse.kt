package com.er453r.neural

class Synapse(val input: Neuron, val output: Neuron, var weight: Float = 0f) {
    var dw: Float = 0.0f
    var learn: Float = 0.0f

    fun getValue() = input.value * weight
}
