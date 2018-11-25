package com.er453r.neural.nets

import com.er453r.neural.Neuron
import kotlin.js.Date
import kotlin.math.floor

class FlatNet : Network {
	private var neurons:MutableList<Neuron> = mutableListOf()

	private var width:Int
	private var height:Int
	private var d:Int = 1

	constructor(width:Int, height:Int, d:Int = 1, getNeuron:()->Neuron) {
		this.width = width
		this.height = height
		this.d = d

		var past:Float = Date.now().toFloat()

		neurons = ArrayList(width * height)

		for(n in 0..neurons.size)
			neurons[n] = getNeuron()

		for(n in 0..neurons.size){
			var neuron:Neuron = neurons[n]

			var x:Int = n % width
			var y:Int = n / width

			var startX:Int = if (x - d < 0) 0 else x - d
			var startY:Int = if (y - d < 0) 0 else y - d

			var endX:Int = if (x + d + 1 > width) width else x + d + 1
			var endY:Int = if (y + d + 1 > height) height else y + d + 1

			for(y_ in startY..endY)
				for(x_ in startX..endX)
					if(y_ != y || x_ != x)
						neuron.addInput(neurons[y_ * width + x_])
		}

		var forwardTime:Float = Date.now().toFloat() - past
		past = Date.now().toFloat()

		for(n in 0..neurons.size){
			var neuron:Neuron = neurons[n]

			for(neighbour in neuron.inputs)
				for(neighbourInput in neighbour.input.inputs)
					if(neighbourInput.input == neuron)
						neuron.outputs.add(neighbourInput)
		}

		var reverseTime:Float = Date.now().toFloat() - past

		println("Created forward in ${1000 * forwardTime} ms., reverse in ${1000 * reverseTime} ms.'")
	}

	override fun getNeurons():MutableList<Neuron>{
		return neurons
	}

	override fun update(){
		for(neuron in neurons)
			neuron.step()

		for(neuron in neurons)
			neuron.propagate()
	}
}