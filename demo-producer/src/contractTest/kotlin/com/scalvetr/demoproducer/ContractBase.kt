package com.scalvetr.demoproducer

import com.ninjasquad.springmockk.MockkBean
import com.scalvetr.demoproducer.service.Employee
import com.scalvetr.demoproducer.service.EmployeeService
import io.mockk.coEvery
import io.restassured.RestAssured
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.web.context.WebApplicationContext


@SpringBootTest(classes = [DemoProducerApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
abstract class ContractBase() {
    @Autowired
    private lateinit var context: WebApplicationContext

    @MockkBean
    private lateinit var employeeService: EmployeeService

    @LocalServerPort
    private var port: Int = 0

    @BeforeEach
    fun setup() {
        RestAssured.baseURI = "http://localhost:" + this.port;
        //RestAssuredMockMvc.webAppContextSetup(this.context);
        val data: MutableMap<String, Employee> = mutableMapOf()
        data["000001"] = Employee("000001", "Name1", "ROLE1")
        data["000002"] = Employee("000002", "Name2", "ROLE2")
        val mockEmployeeService = MockEmployeeService(data)

        coEvery { employeeService.getAll() } answers {
            mockEmployeeService.getAll()
        }
        coEvery { employeeService.create(any()) } answers {
            mockEmployeeService.create(it.invocation.args[0] as Employee)
        }
        coEvery { employeeService.getById(any()) } answers {
            mockEmployeeService.getById(it.invocation.args[0] as String)
        }
        coEvery { employeeService.delete(any()) } answers {
            mockEmployeeService.delete(it.invocation.args[0] as String)
        }
    }

    class MockEmployeeService(private val data: MutableMap<String, Employee> = mutableMapOf()) {
        fun getById(id: String): Employee? {
            return data[id]
        }

        fun getAll(): Flow<Employee> {
            return flow {
                data.values.forEach {
                    emit(it)
                }
            }
        }

        fun create(employee: Employee) {
            data[employee.id] = employee;
        }

        fun delete(id: String): Boolean {
            if (data.containsKey(id)) {
                data.remove(id)
                return true
            }
            return false
        }
    }
}