CREATE TYPE team_member_status AS ENUM ('ACTIVE', 'INACTIVE');

ALTER TABLE projects_employees
ADD COLUMN employeeStatus team_member_status NOT NULL;

CREATE CAST (varchar AS team_member_status) WITH INOUT AS IMPLICIT;