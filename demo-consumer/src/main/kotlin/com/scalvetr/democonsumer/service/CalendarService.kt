package com.scalvetr.democonsumer.service

import kotlinx.coroutines.flow.Flow

interface CalendarService {
    fun getAllVacationRequests(): Flow<VacationRequest>
    suspend fun requestVacation(request: VacationRequest): String
    suspend fun approveVacation(id: String): Boolean
}