package com.er453r.neural.nets

import com.er453r.neural.Neuron

open interface Network {
	open fun getNeurons():MutableList<Neuron?>
	open fun update()
}
