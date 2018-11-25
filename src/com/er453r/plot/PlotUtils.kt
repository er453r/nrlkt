package com.er453r.plot

class PlotUtils {
    companion object {
        fun min(data: List<Float>): Float {
            var minValue: Float = data[0]

            for (n in 1..data.size)
                if (data[n] < minValue)
                    minValue = data[n]

            return minValue
        }

        fun max(data: List<Float>): Float {
            var maxValue: Float = data[0]

            for (n in 1..data.size)
                if (data[n] > maxValue)
                    maxValue = data[n]

            return maxValue
        }
    }
}
