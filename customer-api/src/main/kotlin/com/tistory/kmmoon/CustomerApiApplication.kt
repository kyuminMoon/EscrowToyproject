package com.tistory.kmmoon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
@ComponentScan(basePackages = ["com.tistory.kmmoon"])
class CustomerApiApplication

fun main(args: Array<String>) {
    runApplication<CustomerApiApplication>(*args)
}
