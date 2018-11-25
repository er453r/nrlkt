package com.er453r.plot.colormaps

class Hot : Colormap{
	private static var data:List<List<Float>> = [
		[0, 0, 0],
		[1, 0, 0],
		[1, 1, 0],
		[1, 1, 1]
	]

	fun new(buckets:Int = 266){
		super(data, buckets)
	}
}
