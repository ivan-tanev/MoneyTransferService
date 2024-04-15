CREATE TABLE ACCOUNT
(
    account_id      SERIAL PRIMARY KEY,
    name            VARCHAR(60),
    current_balance DECIMAL
);


CREATE TABLE TRANSACTION
(
    transfer_id      SERIAL PRIMARY KEY,
    account_id       INTEGER,
    transaction_type VARCHAR(50),
    amount           DECIMAL,
    date             TIMESTAMP,

    FOREIGN KEY (account_id) REFERENCES ACCOUNT (account_id)
);

CREATE SEQUENCE ACCOUNT_ID_SEQ START 1;

CREATE SEQUENCE TRANSFER_ID_SEQ START 1;