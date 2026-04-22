CREATE TABLE transactions (
                              id BIGSERIAL PRIMARY KEY,
                              source_account_id BIGINT NOT NULL,
                              destination_account_id BIGINT NOT NULL,
                              amount NUMERIC(19,2) NOT NULL,
                              title VARCHAR(255) NOT NULL,
                              status VARCHAR(50) NOT NULL,
                              created_at TIMESTAMP NOT NULL,
                              processed_at TIMESTAMP,
                              CONSTRAINT fk_transactions_source_account
                                  FOREIGN KEY (source_account_id) REFERENCES bank_accounts(id),
                              CONSTRAINT fk_transactions_destination_account
                                  FOREIGN KEY (destination_account_id) REFERENCES bank_accounts(id),
                              CONSTRAINT chk_transactions_status
                                  CHECK (status IN ('COMPLETED', 'FAILED', 'CANCELED', 'PENDING')),
                              CONSTRAINT chk_transactions_amount
                                  CHECK (amount > 0),
                              CONSTRAINT chk_transactions_different_accounts
                                  CHECK (source_account_id <> destination_account_id)
);