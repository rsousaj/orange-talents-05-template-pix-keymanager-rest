package br.com.zup.orangetalents.rest

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("br.com.zup.orangetalents.rest")
		.start()
}

