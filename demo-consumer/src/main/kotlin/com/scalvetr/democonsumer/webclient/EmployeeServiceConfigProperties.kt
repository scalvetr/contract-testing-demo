package com.scalvetr.democonsumer.webclient

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "config.service.employee")
data class EmployeeServiceConfigProperties(
    var baseUrl: String = "http://employee"
)
