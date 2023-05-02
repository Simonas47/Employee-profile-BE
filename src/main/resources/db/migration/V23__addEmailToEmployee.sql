DROP TABLE users;

ALTER TABLE employees
ADD COLUMN email VARCHAR (100) NOT NULL DEFAULT 'default';

UPDATE employees SET email = 'admin@admin.com' WHERE surname = 'Kazaitis';
UPDATE employees SET email = 'markas@gmail.com' WHERE surname = 'Markaitis';
UPDATE employees SET email = 'laikas@gmail.com' WHERE surname = 'Laikaitis';
UPDATE employees SET email = 'vijolis@gmail.com' WHERE surname = 'Vijolaitis';