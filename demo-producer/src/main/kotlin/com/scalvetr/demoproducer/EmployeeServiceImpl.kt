package com.scalvetr.demoproducer

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.springframework.stereotype.Service

@Service
class EmployeeServiceImpl : EmployeeService {
    var db: MutableMap<String, Employee> = hashMapOf()

    override suspend fun getById(id: String): Employee? {
        return db[id]
    }

    override fun getAll(): Flow<Employee> {
        return flow {
            db.entries.onEach { i -> emit(i.value) }
        }
    }

    override suspend fun create(employee: Employee) {
        db[employee.id] = employee
    }

    override suspend fun delete(id: String) {
        db.remove(id)
    }
}