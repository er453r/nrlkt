package com.er453r.neural

class Neuron(private val mutators: Array<NeuronMutator>) {
    val inputs: MutableList<Synapse> = mutableListOf()
    val outputs: MutableList<Synapse> = mutableListOf()

    var value: Float = 0f
    var fired: Float = 0f

    var learn: Float = 0f
    var learning: Float = 0f

    init {
        mutators.forEach { it.onInit(this) }
    }

    fun addInput(neuron: Neuron) {
        val synapse = Synapse(neuron, this)

        mutators.forEach { it.onSynapse(synapse) }

        inputs.add(synapse)
    }

    fun step() = mutators.forEach { it.onStep(this) }

    fun propagate() {
        value = fired
        learning = learn
    }
}
