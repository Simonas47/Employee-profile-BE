ALTER TABLE projects_employees
ADD COLUMN titleId UUID NOT NULL REFERENCES titles(id);

UPDATE projects_employees SET titleId = title_id;


ALTER TABLE projects_employees
DROP COLUMN title_id;