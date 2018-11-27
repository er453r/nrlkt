package com.er453r.plot.colormaps

import com.er453r.plot.Colormap

class Hot(buckets: Int = 266) : Colormap(data, buckets) {
    companion object {
        val data: Array<Array<Float>> = arrayOf(
                arrayOf(0f, 0f, 0f),
                arrayOf(1f, 0f, 0f),
                arrayOf(1f, 1f, 0f),
                arrayOf(1f, 1f, 1f)
        )
    }
}
