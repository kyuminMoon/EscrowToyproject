package com.tistory.kmmoon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.tistory.kmmoon"])
class ProductApiApplication

fun main(args: Array<String>) {
    runApplication<ProductApiApplication>(*args)
}
