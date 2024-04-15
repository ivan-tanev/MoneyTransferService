package com.tanev.moneytransferservice.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal

data class TransferRequest(
    @field:NotNull(message = "Account id must be specified")
    val accountId: Long,

    @field:NotBlank(message = "Transaction type must be specified")
    val transactionType: String,

    @field:Positive(message = "Amount must be positive")
    val amount: BigDecimal,
)