package com.scalvetr.democonsumer

import com.scalvetr.democonsumer.service.CalendarService
import com.scalvetr.democonsumer.service.VacationRequest
import com.scalvetr.democonsumer.service.VacationRequestStatus
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties
import java.time.LocalDate

@SpringBootTest(
    classes = [DemoConsumerApplication::class],
    properties = [
        "config.service.employee.base-url=http://localhost:8095"
    ], webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureStubRunner(
    ids = [
        "com.scalvetr:demo-producer:+:stubs:8095"], stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
class CalendarServiceTests() {
    @Autowired
    lateinit var calendarService: CalendarService

    @Test
    fun `Given a valid VacationRequest When requestVacation Then requestId is not null`() {
        var req = VacationRequest(
            requester = "000001",
            fromDate = LocalDate.now().plusDays(10),
            toDate = LocalDate.now().plusDays(15),
            status = VacationRequestStatus.PENDING
        )
        runBlocking {
            calendarService.requestVacation(req)
        }

    }
}