package contracts.employee

import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract

contract {
    request {
        method = DELETE
        url = url("/v1/employees/000000")
    }
    response {
        status = NOT_FOUND
    }
}