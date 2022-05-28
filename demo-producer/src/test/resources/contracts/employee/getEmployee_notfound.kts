package contracts.employee

import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract

contract {
    request {
        method = GET
        url = url("/v1/employees/000000")
        headers {
            accept = "application/json"
        }
    }
    response {
        status = NOT_FOUND
    }
}