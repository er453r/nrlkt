package com.er453r.plot.colormaps

import com.er453r.plot.Colormap

class Jet(buckets: Int = 266) : Colormap(data, buckets) {
    companion object {
        val data: Array<Array<Float>> = arrayOf(
                arrayOf(0.0f, 0.0f, 0.5f),
                arrayOf(0.0f, 0.0f, 1.0f),
                arrayOf(0.0f, 0.5f, 1.0f),
                arrayOf(0.0f, 1.0f, 1.0f),
                arrayOf(0.5f, 1.0f, 0.5f),
                arrayOf(1.0f, 1.0f, 0.0f),
                arrayOf(1.0f, 0.5f, 0.0f),
                arrayOf(1.0f, 0.0f, 0.0f),
                arrayOf(0.5f, 0.0f, 0.0f)
        )
    }
}
