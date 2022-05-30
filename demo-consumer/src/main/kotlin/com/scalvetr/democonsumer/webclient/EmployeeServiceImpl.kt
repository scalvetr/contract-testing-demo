package com.scalvetr.democonsumer.webclient

import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Service
class EmployeeServiceImpl(webClientBuilder: WebClient.Builder, employeeServiceConfig: EmployeeServiceConfigProperties) :
    EmployeeService {
    val webClient: WebClient = webClientBuilder.baseUrl(employeeServiceConfig.baseUrl).build()

    override suspend fun getById(id: String): Employee? {
        return webClient
            .get()
            .uri("/v1/employees/${id}")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve().awaitBody()
    }
}