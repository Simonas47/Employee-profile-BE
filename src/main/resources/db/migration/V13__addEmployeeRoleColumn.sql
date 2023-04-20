ALTER TABLE projects_employees
ADD COLUMN title_id      UUID             NOT NULL REFERENCES titles(id);