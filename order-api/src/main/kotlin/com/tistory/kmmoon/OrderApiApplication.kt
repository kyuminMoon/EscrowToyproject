package com.tistory.kmmoon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.tistory.kmmoon"])
class OrderApiApplication

fun main(args: Array<String>) {
    runApplication<OrderApiApplication>(*args)
}
