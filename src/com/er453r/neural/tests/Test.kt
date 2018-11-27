package com.er453r.neural.tests

import com.er453r.neural.Neuron
import com.er453r.neural.mutators.PositiveWeights
import com.er453r.neural.mutators.Decay
import com.er453r.neural.mutators.WTA
import com.er453r.plot.Plot
import com.er453r.plot.colormaps.Viridis
import com.er453r.plot.Image

import com.er453r.neural.nets.FlatNet
import com.er453r.neural.nets.Network
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.w3c.dom.Element
import kotlin.browser.document

fun main(){
	Test()
}

class Test{
	private var output:Image? = null
	private var learning:Image? = null
	private var plot:Plot? = null

	private var fps:FPS = FPS()
	private var stats:Element? = null

	private var network:Network? = null

	private var width:Int = 2 * 32
	private var height:Int = 2 * 32
	
	init{
		document.addEventListener("DOMContentLoaded", {init()})
	}

	private fun init(){
		println("NRLKT Started!")

		stats = document.getElementById("fps")
		output = Image(width, height)
		learning = Image(width, height, Viridis())
		network = FlatNet(width, height, 1) {
			Neuron(mutableListOf(
					WTA(),
					Decay(0.01f, 0.9f),
					PositiveWeights(0.5f),
					DepthLearning()
			))
		}
		plot = Plot(width, height)

		var neurons:MutableList<Neuron?> = network!!.getNeurons()

		var synapses = 0

		for(neuron in neurons)
			synapses += neuron!!.inputs.size

		println("${neurons.size} neurons, ${synapses} synapses")

		loop()
	}

	private var iter:Int = 0

	private var outs:MutableList<Float> = mutableListOf()

	private var log:LogScale = LogScale()

	private fun loop(){
		var inputIndex:Int = (height / 2) * width + (width / 4)
		var outputIndex:Int = (height / 2) * width + (3 * width / 4)
		var neurons:MutableList<Neuron?> = network!!.getNeurons()

		if(iter > 5)
			neurons[inputIndex]!!.value = 1f
		neurons[outputIndex]!!.learning = 1f
		neurons[inputIndex]!!.learning = 0f

		network!!.update()

		output!!.generic(network!!.getNeurons(), fun(neuron:Neuron):Float{
			return log.scale(neuron.value)
		})

		learning!!.generic(network!!.getNeurons(), fun(neuron:Neuron):Float{
			return 1 - log.scale(neuron.learning)
		})

		outputIndex = (height / 2) * width + (3 * width / 4)

		outs.add(network!!.getNeurons().get(outputIndex)!!.value)

		while(outs.size > 100)
			outs.removeAt(0)

		plot!!.floats(outs)

		stats!!.innerHTML = "FPS ${fps.update()}"

		fps.update()


		if(iter > 4)
			neurons[inputIndex]!!.value = 1f
		neurons[outputIndex]!!.learning = 1f
		neurons[inputIndex]!!.learning = 0f

		iter++

		GlobalScope.launch {
			delay(5)

			loop()
		}
	}
}
