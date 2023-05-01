DROP TABLE users;

ALTER TABLE employees
ADD COLUMN email VARCHAR(100) NOT NULL DEFAULT 'default';

UPDATE employees
SET email = 'admin@admin.com'
WHERE surname = 'Kazaitis';
