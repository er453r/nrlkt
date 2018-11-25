package com.er453r.neural.tests

import com.er453r.neural.mutators.LearningWTA
import com.er453r.neural.mutators.PositiveWeights
import com.er453r.neural.mutators.Decay
import com.er453r.neural.mutators.WTA
import com.er453r.plot.Plot
import haxe.ds.Vector
import haxe.Timer

import js.html.Element
import js.Browser

import com.er453r.plot.colormaps.Viridis
import com.er453r.plot.Image

import com.er453r.neural.nets.FlatNet
import com.er453r.neural.nets.Network
import org.w3c.dom.Element

class Test{
	private var output:Image
	private var learning:Image
	private var plot:Plot

	private var fps:FPS = FPS()
	private var stats:Element

	private var network:Network

	private var width:Int = 2 * 32
	private var height:Int = 2 * 32

	fun main(){
		new Test()
	}

	fun new (){
		Browser.document.addEventListener("DOMContentLoaded", init)
	}

	private fun init(){
		stats = Browser.document.getElementById("fps")
		output = new Image(width, height)
		learning = new Image(width, height, new Viridis())
		network = new FlatNet(width, height, 1, fun(){
			return new Neuron([
				new WTA(),
				new Decay(0.01, 0.9),
				new PositiveWeights(0.5),
				new DepthLearning()
			])
		})
		plot = new Plot(width, height)

		var neurons:MutableList<Neuron> = network.getNeurons()

		var synapses:Int = 0

		for(neuron in neurons)
			synapses += neuron.inputs.size

		trace('${neurons.size} neurons, ${synapses} synapses')

		loop()
	}

	private var iter:Int = 0

	private var outs:List<Float> = emptyArray()

	private var log:LogScale = new LogScale()

	private fun loop(){
		var inputIndex:Int = Std.int(height / 2) * width + Std.int(width / 4)
		var outputIndex:Int = Std.int(height / 2) * width + Std.int(3 * width / 4)
		var neurons:MutableList<Neuron> = network.getNeurons()

		if(iter > 5)
			neurons[inputIndex].value = 1
		neurons[outputIndex].learning = 1
		neurons[inputIndex].learning = 0

		network.update()

		output.generic(network.getNeurons(), fun(neuron:Neuron):Float{
			return log.scale(neuron.value)
		})

		learning.generic(network.getNeurons(), fun(neuron:Neuron):Float{
			return 1 - log.scale(neuron.learning)
		})

		var outputIndex:Int = Std.int(height / 2) * width + Std.int(3 * width / 4)

		outs.push(network.getNeurons().get(outputIndex).value)

		while(outs.size > 100)
			outs.shift()

		plot.floats(outs)

		stats.innerHTML = 'FPS ${fps.update()}'

		fps.update()


		if(iter > 4)
			neurons[inputIndex].value = 1
		neurons[outputIndex].learning = 1
		neurons[inputIndex].learning = 0

		iter++

		Timer.delay(loop, 5)
	}
}
