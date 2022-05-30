package com.scalvetr.democonsumer

import com.scalvetr.democonsumer.webclient.EmployeeServiceConfigProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(EmployeeServiceConfigProperties::class)
class DemoConsumerApplication

fun main(args: Array<String>) {
    runApplication<DemoConsumerApplication>(*args)
}
