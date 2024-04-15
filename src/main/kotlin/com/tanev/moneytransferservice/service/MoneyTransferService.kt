package com.tanev.moneytransferservice.service

import com.tanev.generated.Sequences
import com.tanev.generated.Tables.TRANSACTION
import com.tanev.generated.tables.Account.ACCOUNT
import com.tanev.generated.tables.records.AccountRecord
import com.tanev.generated.tables.records.TransactionRecord
import com.tanev.moneytransferservice.model.*
import com.tanev.moneytransferservice.model.TransactionType.DEPOSIT
import com.tanev.moneytransferservice.model.TransactionType.WITHDRAW
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MoneyTransferService : BaseService() {

    fun getAccountDetailsById(accountId: Long): MoneyAccount? =
        db.fetchOne(ACCOUNT, ACCOUNT.ACCOUNT_ID.eq(accountId.toInt()))
            ?.into(AccountRecord())
            ?.mapToInternalModel()

    fun createNewMoneyAccount(newAccount: AccountRequest): MoneyAccount? {
        val moneyAccount = MoneyAccount(
            accountId = getNewAccountId(),
            name = newAccount.name,
            currentBalance = newAccount.initialBalance
        )

        return db.transactionResult { transaction ->
            transaction.dsl().executeInsert(moneyAccount.mapToRecord())
            moneyAccount
        }
    }

    fun fetchAllTransfersByAccountId(accountId: Long): List<TransferRecordView> =
        with(TRANSACTION) {
            db.selectFrom(this).where(ACCOUNT_ID.eq(accountId.toInt()))
                .fetchInto(TransactionRecord::class.java)
                .map {
                    TransferRecordView(
                        it.transferId,
                        TransactionType.valueOf(it.transactionType),
                        it.amount,
                        it.date
                    )
                }
        }


    fun createTransactionRecord(transferRequest: TransferRequest): TransferRecord? {
        val account = getAccountDetailsById(transferRequest.accountId)
        require(account != null) { NullPointerException("Account with this ID does not exist") }

        if (TransactionType.valueOf(transferRequest.transactionType.uppercase()) == WITHDRAW) {
            require(account.currentBalance >= transferRequest.amount) {
                UnsupportedOperationException("The amount of money you are trying to withdraw is more that you have in you're account")
            }
        }

        val newTransfer = TransferRecord(
            transferId = getNewTransferId(),
            accountId = transferRequest.accountId,
            transactionType = TransactionType.valueOf(transferRequest.transactionType.uppercase()),
            amount = transferRequest.amount,
            date = LocalDateTime.now()
        )

        return db.transactionResult { transaction ->
            transaction.dsl().executeInsert(newTransfer.mapToRecord())

            val updatedAccount = when (newTransfer.transactionType) {
                DEPOSIT -> account.copy(currentBalance = account.currentBalance.plus(transferRequest.amount))
                WITHDRAW -> account.copy(currentBalance = account.currentBalance.minus(transferRequest.amount))
            }

            transaction.dsl().executeUpdate(updatedAccount.mapToRecord())
            newTransfer
        }
    }


    private fun getNewAccountId() =
        db.select(Sequences.ACCOUNT_ID_SEQ.nextval()).fetchOneInto(Long::class.java)!!

    private fun getNewTransferId() =
        db.select(Sequences.TRANSFER_ID_SEQ.nextval()).fetchOneInto(Long::class.java)!!


    private fun MoneyAccount.mapToRecord() = AccountRecord(
        this.accountId.toInt(),
        this.name,
        this.currentBalance
    )

    private fun TransferRecord.mapToRecord() = TransactionRecord(
        this.transferId.toInt(),
        this.accountId.toInt(),
        this.transactionType.name,
        this.amount,
        this.date
    )

    private fun AccountRecord.mapToInternalModel() = MoneyAccount(
        accountId = this.accountId.toLong(),
        name = this.name,
        currentBalance = this.currentBalance,
    )
}
