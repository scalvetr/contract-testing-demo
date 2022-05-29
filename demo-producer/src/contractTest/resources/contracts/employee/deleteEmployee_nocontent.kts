package contracts.employee

import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract

contract {
    request {
        method = DELETE
        url = url("/v1/employees/000002")
    }
    response {
        status = NO_CONTENT
    }
}