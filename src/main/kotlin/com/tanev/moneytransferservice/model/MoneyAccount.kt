package com.tanev.moneytransferservice.model

import java.math.BigDecimal

data class MoneyAccount(
    val accountId: Long,
    val name: String,
    val currentBalance: BigDecimal,
)