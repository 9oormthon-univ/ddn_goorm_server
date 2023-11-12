package com.ddn.goorm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GoormApplication

fun main(args: Array<String>) {
	runApplication<GoormApplication>(*args)
}
