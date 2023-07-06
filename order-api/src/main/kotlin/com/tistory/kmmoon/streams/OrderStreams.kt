package com.tistory.kmmoon.streams

import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.Output
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.context.annotation.Bean
import org.springframework.messaging.MessageChannel

import org.springframework.messaging.SubscribableChannel
import org.springframework.stereotype.Service
import java.util.function.Consumer

//interface OrderStreams {
//    @Input(INPUT)
//    fun inboundGreetings(): SubscribableChannel?
//
//    @Output(OUTPUT)
//    fun outboundGreetings(): MessageChannel?
//
//    companion object {
//        const val INPUT = "greetings-in"
//        const val OUTPUT = "greetings-out"
//    }
//}

//@Service
//@EnableBinding(BindableGradesChannel::class)
//class OrderStreams {
//    private val log = LoggerFactory.getLogger(OrderStreams::class.java)
//
//    @StreamListener(BindableScoresChannel.INPUT)
//    fun listen(grade: Grade) {
//        log.info("Received $grade")
//        // do something
//    }
//}

/**
 * 위 2개의 코드는 동일하게 동작하나, Deprecate 되어 찾아본 결과 yml을 사용한 방식으로 변경됐다고 함.
 * @link https://stackoverflow.com/questions/65978055/enablebinding-output-input-deprecated-since-version-of-3-1-of-spring-cloud-str
 * */
@Service
class OrderStreams {
    private val log = LoggerFactory.getLogger(OrderStreams::class.java)

    @Bean
    fun gradesChannel(): Consumer<Grade> {
        return Consumer { listen(grade = it) }
    }

    fun listen(grade: Grade) {
        log.info("Received $grade")
        // do something
    }
}