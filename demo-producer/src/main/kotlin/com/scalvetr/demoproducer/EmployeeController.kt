package com.scalvetr.demoproducer

import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/v1/employees"])
class EmployeeController(val employeeService: EmployeeService) {

    @GetMapping("/{id}")
    suspend fun get(@PathVariable("id") id: String): Employee =
        employeeService.getById(id)

    @GetMapping("")
    fun get(): Flow<Employee> =
        employeeService.getAll()

    @PostMapping("")
    suspend fun create(@RequestBody employee: Employee) =
        employeeService.create(employee)

}