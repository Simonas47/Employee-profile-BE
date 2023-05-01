ALTER TABLE projects_employees
ADD COLUMN titleId INTEGER NOT NULL REFERENCES titles(id),
ADD COLUMN responsibilities VARCHAR(2000);
