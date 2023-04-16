ALTER TABLE employees DROP COLUMN hiringDate;
ALTER TABLE employees DROP COLUMN exitDate;

CREATE TABLE IF NOT EXISTS employment_dates(
	id              UUID        DEFAULT uuid_generate_v4() NOT NULL CONSTRAINT employment_dates_pk PRIMARY KEY,
	employeeId      UUID        CONSTRAINT employment_dates_employee_id_fk REFERENCES employees,
	hiringDate      DATE        NOT NULL,
	exitDate        DATE
);


INSERT INTO employment_dates (hiringDate, exitDate, employeeId) VALUES
('2018-04-11T14:35:46.000Z','2020-04-11T14:35:46.000Z', (SELECT id FROM employees WHERE surname = 'Kazaitis')),
('2022-04-11T14:35:46.000Z', null, (SELECT id FROM employees WHERE surname = 'Kazaitis'));
