package com.scalvetr.democonsumer

import com.scalvetr.democonsumer.service.CalendarService
import com.scalvetr.democonsumer.service.VacationRequest
import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/v1/calendar"], produces = [MediaType.APPLICATION_JSON_VALUE])
class CalendarController(val calendarService: CalendarService) {


    @GetMapping("/vacations")
    fun getAllVacationRequest(): Flow<VacationRequest> =
        calendarService.getAllVacationRequests()

    @PostMapping(path = ["/vacations"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(code = HttpStatus.CREATED)
    suspend fun requestVacation(@RequestBody request: VacationRequest) =
        VacationId(calendarService.requestVacation(request))

    @PutMapping(path = ["/vacations/{id}"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(code = HttpStatus.OK)
    suspend fun approveVacation(@PathVariable("id") id: String) {
        if (!calendarService.approveVacation(id)) {
            throw NotFoundException()
        }
    }
}