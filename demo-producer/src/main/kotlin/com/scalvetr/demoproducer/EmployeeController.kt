package com.scalvetr.demoproducer

import com.scalvetr.demoproducer.service.Employee
import com.scalvetr.demoproducer.service.EmployeeService
import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/v1/employees"], produces = [MediaType.APPLICATION_JSON_VALUE])
class EmployeeController(val employeeService: EmployeeService) {

    @GetMapping("/{id}")
    suspend fun get(@PathVariable("id") id: String): Employee =
        employeeService.getById(id) ?: throw NotFoundException()

    @GetMapping("")
    fun get(): Flow<Employee> =
        employeeService.getAll()

    @PostMapping(path = [""], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(code = HttpStatus.CREATED)
    suspend fun create(@RequestBody employee: Employee) =
        employeeService.create(employee)

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    suspend fun delete(@PathVariable("id") id: String) {
        if (!employeeService.delete(id)) {
            throw NotFoundException()
        }
    }

}