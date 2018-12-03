package com.er453r.neural

interface NeuronMutator {
    fun onInit(neuron: Neuron) {}
    fun onStep(neuron: Neuron) {}
    fun onSynapse(synapse: Synapse) {}
}
