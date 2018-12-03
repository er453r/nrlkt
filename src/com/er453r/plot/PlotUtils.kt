package com.er453r.plot

class PlotUtils {
    companion object {
        fun min(data: FloatArray): Float {
            var minValue: Float = data[0]

            for (n in 1 until data.size)
                if (data[n] < minValue)
                    minValue = data[n]

            return minValue
        }

        fun max(data: FloatArray): Float {
            var maxValue: Float = data[0]

            for (n in 1 until data.size)
                if (data[n] > maxValue)
                    maxValue = data[n]

            return maxValue
        }
    }
}
