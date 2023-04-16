package com.tistory.kmmoon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.tistory.kmmoon"])
class PaymentApiApplication

fun main(args: Array<String>) {
    runApplication<PaymentApiApplication>(*args)
}
