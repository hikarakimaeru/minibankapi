ALTER TABLE accounts
ADD CONSTRAINT fk_accounts_user
FOREIGN KEY(user_id)
REFERENCES users;