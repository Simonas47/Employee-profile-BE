ALTER TABLE projects_employees
ADD COLUMN projectEmployeeStartDate DATE NOT NULL DEFAULT CURRENT_DATE,
ADD COLUMN projectEmployeeEndDate DATE;

ALTER TABLE projects_employees
RENAME COLUMN teamMemberStatus TO projectEmployeeStatus;
