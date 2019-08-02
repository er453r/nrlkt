package com.er453r.neural.utils

// SKIP_SOURCEMAP_REMAPPING
class Log(val name:String) {
    inline fun info(message:(() -> String)){
        console.log("Info[$name] : ${message()}")
    }
}
