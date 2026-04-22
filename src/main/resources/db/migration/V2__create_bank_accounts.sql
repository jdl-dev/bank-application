CREATE TABLE bank_accounts (
                               id BIGSERIAL PRIMARY KEY,
                               account_number VARCHAR(50) NOT NULL UNIQUE,
                               currency VARCHAR(10) NOT NULL,
                               balance NUMERIC(19,2) NOT NULL,
                               status VARCHAR(50) NOT NULL,
                               owner_id BIGINT NOT NULL,
                               created_at TIMESTAMP NOT NULL,
                               updated_at TIMESTAMP NOT NULL,
                               CONSTRAINT fk_bank_accounts_owner
                                   FOREIGN KEY (owner_id) REFERENCES users(id),
                               CONSTRAINT chk_bank_accounts_status
                                   CHECK (status IN ('ACTIVE', 'BLOCKED', 'CLOSED'))
);