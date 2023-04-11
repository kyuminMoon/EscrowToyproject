package com.tistory.kmmoon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PaymentFrontApiApplication

fun main(args: Array<String>) {
    runApplication<PaymentFrontApiApplication>(*args)
}
