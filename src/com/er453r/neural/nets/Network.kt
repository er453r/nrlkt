package com.er453r.neural.nets

import com.er453r.neural.Neuron

interface Network {
    fun getNeurons(): MutableList<Neuron?>
    fun update()
}
