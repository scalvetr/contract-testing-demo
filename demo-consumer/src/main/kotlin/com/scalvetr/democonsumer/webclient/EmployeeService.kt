package com.scalvetr.democonsumer.webclient

interface EmployeeService {
    suspend fun getById(id: String): Employee?
}