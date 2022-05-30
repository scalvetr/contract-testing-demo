package com.scalvetr.democonsumer.service

import com.scalvetr.democonsumer.webclient.EmployeeService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.springframework.stereotype.Service
import java.util.*

@Service
class CalendarServiceImpl(val employeeService: EmployeeService) : CalendarService {
    var data: MutableMap<String, VacationRequest> = hashMapOf()
    override fun getAllVacationRequests(): Flow<VacationRequest> {
        return flow {
            data.entries.forEach { emit(it.value) }
        }
    }

    override suspend fun requestVacation(request: VacationRequest): String {
        if (employeeService.getById(request.requester) == null) {
            throw EmployeeNotFoundException(request.requester)
        }
        val id = UUID.randomUUID().toString()
        data[id] = request
        return id
    }

    override suspend fun approveVacation(id: String): Boolean {
        if (data.containsKey(id)) {
            data[id]?.status = VacationRequestStatus.APPROVED
            return true
        }
        return false
    }
}