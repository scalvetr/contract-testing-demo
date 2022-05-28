package com.scalvetr.demoproducer

import io.restassured.config.EncoderConfig
import io.restassured.module.mockmvc.RestAssuredMockMvc
import io.restassured.module.mockmvc.config.RestAssuredMockMvcConfig
import kotlinx.coroutines.flow.Flow
import org.junit.jupiter.api.BeforeEach

open class EmployeeBase {
    @BeforeEach
    fun setup() {
        val encoderConfig: EncoderConfig = EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)
        val restAssuredConf: RestAssuredMockMvcConfig = RestAssuredMockMvcConfig().encoderConfig(encoderConfig)
        RestAssuredMockMvc.config = restAssuredConf
        RestAssuredMockMvc.standaloneSetup(EmployeeController(stubbedEmployeeService()))
    }

    private fun stubbedEmployeeService(): EmployeeService {
        return object : EmployeeService {
            override suspend fun getById(id: String): Employee {
                return Employee(
                    id = "12345",
                    name = "Name1",
                    role = "Role1"
                )
            }

            override fun getAll(): Flow<Employee> {
                TODO("Not yet implemented")
            }

            override suspend fun create(employee: Employee): Any {
                TODO("Not yet implemented")
            }
        }
    }
}