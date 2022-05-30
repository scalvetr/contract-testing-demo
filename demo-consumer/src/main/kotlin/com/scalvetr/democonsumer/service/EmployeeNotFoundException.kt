package com.scalvetr.democonsumer.service


class EmployeeNotFoundException(employeeId: String) :
    RuntimeException("Employee not found $employeeId") {}