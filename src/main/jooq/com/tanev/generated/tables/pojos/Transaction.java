/*
 * This file is generated by jOOQ.
 */
package com.tanev.generated.tables.pojos;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Integer transferId;
    private final Integer accountId;
    private final String transactionType;
    private final BigDecimal amount;
    private final LocalDateTime date;

    public Transaction(Transaction value) {
        this.transferId = value.transferId;
        this.accountId = value.accountId;
        this.transactionType = value.transactionType;
        this.amount = value.amount;
        this.date = value.date;
    }

    public Transaction(
        Integer transferId,
        Integer accountId,
        String transactionType,
        BigDecimal amount,
        LocalDateTime date
    ) {
        this.transferId = transferId;
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = date;
    }

    /**
     * Getter for <code>public.transaction.transfer_id</code>.
     */
    public Integer getTransferId() {
        return this.transferId;
    }

    /**
     * Getter for <code>public.transaction.account_id</code>.
     */
    public Integer getAccountId() {
        return this.accountId;
    }

    /**
     * Getter for <code>public.transaction.transaction_type</code>.
     */
    public String getTransactionType() {
        return this.transactionType;
    }

    /**
     * Getter for <code>public.transaction.amount</code>.
     */
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * Getter for <code>public.transaction.date</code>.
     */
    public LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Transaction other = (Transaction) obj;
        if (this.transferId == null) {
            if (other.transferId != null)
                return false;
        }
        else if (!this.transferId.equals(other.transferId))
            return false;
        if (this.accountId == null) {
            if (other.accountId != null)
                return false;
        }
        else if (!this.accountId.equals(other.accountId))
            return false;
        if (this.transactionType == null) {
            if (other.transactionType != null)
                return false;
        }
        else if (!this.transactionType.equals(other.transactionType))
            return false;
        if (this.amount == null) {
            if (other.amount != null)
                return false;
        }
        else if (!this.amount.equals(other.amount))
            return false;
        if (this.date == null) {
            if (other.date != null)
                return false;
        }
        else if (!this.date.equals(other.date))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.transferId == null) ? 0 : this.transferId.hashCode());
        result = prime * result + ((this.accountId == null) ? 0 : this.accountId.hashCode());
        result = prime * result + ((this.transactionType == null) ? 0 : this.transactionType.hashCode());
        result = prime * result + ((this.amount == null) ? 0 : this.amount.hashCode());
        result = prime * result + ((this.date == null) ? 0 : this.date.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Transaction (");

        sb.append(transferId);
        sb.append(", ").append(accountId);
        sb.append(", ").append(transactionType);
        sb.append(", ").append(amount);
        sb.append(", ").append(date);

        sb.append(")");
        return sb.toString();
    }
}
