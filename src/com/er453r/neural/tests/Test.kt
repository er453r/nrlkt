package com.er453r.neural.tests

import com.er453r.neural.Neuron
import com.er453r.neural.mutators.Decay
import com.er453r.neural.mutators.PositiveWeights
import com.er453r.neural.mutators.WTA
import com.er453r.neural.nets.FlatNet
import com.er453r.neural.nets.Network
import com.er453r.plot.Image
import com.er453r.plot.Plot
import com.er453r.plot.colormaps.Inferno
import com.er453r.plot.colormaps.Viridis
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.w3c.dom.Element
import kotlin.browser.document
import kotlin.browser.window
import kotlin.js.Date

class Test {
    private val output: Image
    private val learning: Image
    private val plot: Plot

    private val fps: FPS = FPS()
    private val stats: Element

    private val network: Network

    private val width: Int = 2 * 32
    private val height: Int = 2 * 32

    private val inputIndex: Int = (height / 2) * width + (width / 4)
    private val outputIndex: Int = (height / 2) * width + (3 * width / 4)

    private var iter: Int = 0

    private val outs: MutableList<Float> = mutableListOf()
    private val log: LogScale = LogScale()

    private var past: Float = Date.now().toFloat()

    init {
        println("NRLKT Started!")

        stats = document.getElementById("fps")!!
        output = Image(width, height, Inferno())
        learning = Image(width, height, Viridis())
        network = FlatNet(width, height, 1) {
            Neuron(arrayOf(
                    WTA(),
                    Decay(0.01f, 0.9f),
                    PositiveWeights(0.5f),
                    DepthLearning()
            ))
        }
        plot = Plot(width, height)

        val neurons: Array<Neuron> = network.getNeurons()

        var synapses = 0

        for (neuron in neurons)
            synapses += neuron.inputs.size

        println("${neurons.size} neurons, $synapses synapses")

        past = Date.now().toFloat()

        loop()
    }

    private fun loop() {
        val neurons = network.getNeurons()

        if (iter > 5)
            neurons[inputIndex].value = 1f

        neurons[outputIndex].learning = 1f
        neurons[inputIndex].learning = 0f

        network.update()

        val log = this.log
        val outs = this.outs

        output.generic(neurons) { log.scale(it.value) }

        learning.generic(neurons) { 1 - log.scale(it.learning) }

        outs.add(neurons[outputIndex].value)

        while (outs.size > 100)
            outs.removeAt(0)

        plot.floats(FloatArray(outs.size){outs[it]})

        if (iter % 100 == 0)
            stats.innerHTML = "FPS ${fps.update()}"
        else
            fps.update()

        if (iter > 4)
            neurons[inputIndex].value = 1f

        neurons[outputIndex].learning = 1f
        neurons[inputIndex].learning = 0f

        iter++

        if (iter < 1000)
            GlobalScope.launch {
                delay(1)
                loop()
            }
        else{
            val now: Float = Date.now().toFloat()

            val diff: Float = (now - past) / 1e3f

            println("Done in $diff")
        }
    }
}
