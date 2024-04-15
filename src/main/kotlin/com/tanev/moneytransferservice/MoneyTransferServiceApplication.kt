package com.tanev.moneytransferservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MoneyTransferServiceApplication

fun main(args: Array<String>) {
    runApplication<MoneyTransferServiceApplication>(*args)
}
