package com.scalvetr.demoproducer

import io.restassured.config.EncoderConfig
import io.restassured.module.mockmvc.RestAssuredMockMvc
import io.restassured.module.mockmvc.config.RestAssuredMockMvcConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.BeforeEach

open class EmployeeBase(val data: MutableMap<String, Employee> = mutableMapOf()) {
    @BeforeEach
    fun setup() {
        val encoderConfig: EncoderConfig = EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)
        val restAssuredConf: RestAssuredMockMvcConfig = RestAssuredMockMvcConfig().encoderConfig(encoderConfig)
        RestAssuredMockMvc.config = restAssuredConf
        RestAssuredMockMvc.standaloneSetup(EmployeeController(stubbedEmployeeService()))
        data["000001"] = Employee("000001", "Name", "ROLE1")
    }

    private fun stubbedEmployeeService(): EmployeeService {

        return object : EmployeeService {
            override suspend fun getById(id: String): Employee? {
                return data[id]
            }

            override fun getAll(): Flow<Employee> {
                return flow {
                    data.values.forEach {
                        emit(it)
                    }
                }

            }

            override suspend fun create(employee: Employee) {
                data[employee.id] = employee;
            }

            override suspend fun delete(id: String) {
                data.remove(id)
            }
        }
    }
}