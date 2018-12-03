package com.er453r.neural

class Neuron(private val mutators: MutableList<NeuronMutator>) {
    val inputs: MutableList<Synapse> = mutableListOf()
    val outputs: MutableList<Synapse> = mutableListOf()

    var value: Float = 0f
    var fired: Float = 0f

    var learn: Float = 0f
    var learning: Float = 0f

    init {
        for (mutator in mutators)
            mutator.onInit(this)
    }

    fun addInput(neuron: Neuron) {
        val synapse = Synapse(neuron, this)

        for (mutator in mutators)
            mutator.onSynapse(synapse)

        inputs.add(synapse)
    }

    fun step() {
        for (mutator in mutators)
            mutator.onStep(this)
    }

    fun propagate() {
        value = fired
        learning = learn
    }
}
