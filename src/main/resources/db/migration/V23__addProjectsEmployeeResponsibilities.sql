ALTER TABLE projects_employees
ADD COLUMN titleId INTEGER REFERENCES titles(id),
ADD COLUMN responsibilities VARCHAR(2000);
