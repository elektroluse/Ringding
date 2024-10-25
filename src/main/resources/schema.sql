DROP TABLE IF EXISTS "phonebook";

CREATE TABLE phonebook
(
    record_id SERIAL PRIMARY KEY,
    phone_num text,
    name text,
    is_company bool,
    created_at timestamp

);
