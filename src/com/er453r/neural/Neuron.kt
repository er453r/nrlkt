package com.er453r.neural

class Neuron(private vararg val mutators: NeuronMutator) {
    var inputs: Array<Synapse> = emptyArray()
    var outputs: Array<Synapse> = emptyArray()

    var value: Float = 0f
    var fired: Float = 0f

    var learn: Float = 0f
    var learning: Float = 0f

    init {
        mutators.forEach { it.onInit(this) }
    }

    fun addInputs(neurons: List<Neuron>) {
        inputs = Array(neurons.size) { n ->
            val synapse = Synapse(neurons[n], this)

            mutators.forEach { it.onSynapse(synapse) }

            synapse
        }
    }

    fun addOutputs(synapses: List<Synapse>) {
        outputs = synapses.toTypedArray()
    }

    fun step() = mutators.forEach { it.onStep(this) }

    fun propagate() {
        value = fired
        learning = learn
    }
}
