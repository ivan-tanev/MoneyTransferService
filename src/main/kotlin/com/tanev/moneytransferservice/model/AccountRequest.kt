package com.tanev.moneytransferservice.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import java.math.BigDecimal

data class AccountRequest(
    @field:NotBlank(message = "Account name must be specified")
    val name: String,

    @field:Positive(message = "Initial balance must be positive")
    val initialBalance: BigDecimal,
)