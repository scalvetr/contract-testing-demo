package employees

import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract

contract {
    request {
        method = GET
        url = url("/v1/employees/000001")
        headers {
            accept = "application/json"
        }
    }
    response {
        status = OK
        body = body(
            "id" to "000001",
            "name" to value(regex("[0-9 a-z A-Z]*")),
            "role" to value(regex("[0-9 a-z A-Z]*"))
        )
        headers {
            contentType = "application/json"
        }
    }
}