package employees

import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract

contract {
    request {
        method = GET
        url = url("/employees")
        headers {
            accept = "application/json"
        }
    }
    response {
        status = OK
        body = body(
            "id" to value(regex("[0-9]{10}")),
            "name" to "Name1",
            "role" to "Role1"
        )
        headers {
            contentType = "application/json"
        }
    }
}