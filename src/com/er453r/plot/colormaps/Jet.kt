package com.er453r.plot.colormaps

class Jet : Colormap{
	private static var data:List<List<Float>> = [
		[0.0, 0.0, 0.5],
		[0.0, 0.0, 1.0],
		[0.0, 0.5, 1.0],
		[0.0, 1.0, 1.0],
		[0.5, 1.0, 0.5],
		[1.0, 1.0, 0.0],
		[1.0, 0.5, 0.0],
		[1.0, 0.0, 0.0],
		[0.5, 0.0, 0.0]
	]

	fun new(buckets:Int = 266){
		super(data, buckets)
	}
}
