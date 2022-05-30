package com.scalvetr.democonsumer.service

import java.time.LocalDate

data class VacationRequest(
    val requester: String,
    val fromDate: LocalDate,
    val toDate: LocalDate,
    var status: VacationRequestStatus = VacationRequestStatus.PENDING

)

enum class VacationRequestStatus {
    PENDING,
    APPROVED,
    REJECTED
}