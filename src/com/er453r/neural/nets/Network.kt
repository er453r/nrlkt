package com.er453r.neural.nets

import com.er453r.neural.Neuron

interface Network {
    fun getNeurons(): Array<Neuron>
    fun update()
}
