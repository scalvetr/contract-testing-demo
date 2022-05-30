package com.scalvetr.demoproducer.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.springframework.stereotype.Service

@Service
class EmployeeServiceImpl : EmployeeService {
    var data: MutableMap<String, Employee> = hashMapOf()

    override suspend fun getById(id: String): Employee? {
        return data[id]
    }

    override fun getAll(): Flow<Employee> {
        return flow {
            data.entries.onEach { i -> emit(i.value) }
        }
    }

    override suspend fun create(employee: Employee) {
        data[employee.id] = employee
    }

    override suspend fun delete(id: String): Boolean {
        if (data.containsKey(id)) {
            data.remove(id)
            return true
        }
        return false
    }
}