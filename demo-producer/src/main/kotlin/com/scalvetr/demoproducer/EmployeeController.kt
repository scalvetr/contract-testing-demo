package com.scalvetr.demoproducer

import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/v1/employees"], produces = [MediaType.APPLICATION_JSON_VALUE])
class EmployeeController(val employeeService: EmployeeService) {

    @GetMapping("/{id}")
    suspend fun get(@PathVariable("id") id: String): Employee =
        employeeService.getById(id)

    @GetMapping("")
    fun get(): Flow<Employee> =
        employeeService.getAll()

    @PostMapping(path = [""], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(code = HttpStatus.CREATED)
    suspend fun create(@RequestBody employee: Employee) =
        employeeService.create(employee)

}