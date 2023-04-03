CREATE TYPE employee_status AS ENUM ('ACTIVE', 'INACTIVE', 'DISMISSED');

ALTER TABLE employees
ADD COLUMN status employee_status NOT NULL;