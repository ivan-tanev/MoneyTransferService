package com.tanev.moneytransferservice.model

import java.math.BigDecimal
import java.time.LocalDateTime

data class TransferRecord(
    val transferId: Long,
    val accountId: Long,
    val transactionType: TransactionType,
    val amount: BigDecimal,
    val date: LocalDateTime,
)