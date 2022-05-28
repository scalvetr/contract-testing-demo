package com.scalvetr.demoproducer

import kotlinx.coroutines.flow.Flow

interface EmployeeService {
    suspend fun getById(id: String): Employee?
    fun getAll(): Flow<Employee>
    suspend fun create(employee: Employee)
    suspend fun delete(id: String): Boolean
}