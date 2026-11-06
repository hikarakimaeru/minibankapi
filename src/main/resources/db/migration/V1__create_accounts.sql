CREATE TABLE accounts
(
    account_number VARCHAR(255) PRIMARY KEY,
    user_id        INTEGER        NOT NULL,
    balance        NUMERIC(18, 2) NOT NULL DEFAULT 0
);