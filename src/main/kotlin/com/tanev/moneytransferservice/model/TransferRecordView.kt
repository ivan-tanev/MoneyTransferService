package com.tanev.moneytransferservice.model

import java.math.BigDecimal
import java.time.LocalDateTime

data class TransferRecordView(
    val transferId: Int,
    val transactionType: TransactionType,
    val amount: BigDecimal,
    val date: LocalDateTime,
)