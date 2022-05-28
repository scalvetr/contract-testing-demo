package employees

import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract

contract {
    request {
        method = POST
        url = url("/v1/employees")
        body = body(
            "id" to value(regex("[0-9]{10}")),
            "name" to value(regex("[0-9 a-z A-Z]*")),
            "role" to value(regex("[0-9 a-z A-Z]*"))
        )
        headers {
            contentType = "application/json"
        }
    }
    response {
        status = CREATED
    }
}