package employees

import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract

contract {
    request {
        method = POST
        url = url("/v1/employees")
        body = body(
            "id" to value(regex("[0-9]{10}")),
            "name" to "99999",
            "role" to "99999"
        )
        headers {
            contentType = "application/json"
        }
    }
    response {
        status = CREATED
    }
}