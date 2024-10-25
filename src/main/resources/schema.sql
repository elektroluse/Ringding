DROP TABLE IF EXISTS "records";

CREATE TABLE records
(
    record_id SERIAL PRIMARY KEY,
    phone_num text,
    name text,
    is_company bool,
    created_at timestamp

);
