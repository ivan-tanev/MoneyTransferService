package com.tanev.moneytransferservice.controller

import com.tanev.moneytransferservice.model.AccountRequest
import com.tanev.moneytransferservice.model.MoneyAccount
import com.tanev.moneytransferservice.model.TransferRequest
import com.tanev.moneytransferservice.service.MoneyTransferService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
@Validated
class MoneyTransferController {

    @Autowired
    private lateinit var moneyTransferService: MoneyTransferService


    @GetMapping("/account/{accountId}")
    fun getAccountDetails(@PathVariable accountId: Long): MoneyAccount? =
        moneyTransferService.getAccountDetailsById(accountId)

    @PostMapping("/account")
    fun createNewMoneyAccount(@Valid @RequestBody newAccount: AccountRequest) =
        moneyTransferService.createNewMoneyAccount(newAccount)

    @GetMapping("/account/{accountId}/transfers")
    fun fetchAllTransfersByAccountId(@PathVariable accountId: Long) =
        moneyTransferService.fetchAllTransfersByAccountId(accountId)

    @PostMapping("/transfer")
    fun createTransactionRecord(@Valid @RequestBody transferRequest: TransferRequest) =
        moneyTransferService.createTransactionRecord(transferRequest)

}