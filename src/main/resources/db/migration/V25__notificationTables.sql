CREATE TYPE NOTIFICATION_TYPE AS ENUM (
    'ADD_EMPLOYEE',
    'REMOVE_EMPLOYEE',
    'UPDATE_PROJECT_STATUS',
    'UPDATE_PROJECT_INFORMATION'
);

CREATE TABLE IF NOT EXISTS notifications
(
    id          INTEGER     GENERATED ALWAYS AS IDENTITY NOT NULL CONSTRAINT notification_pk PRIMARY KEY,
	employeeId  INTEGER     NOT NULL CONSTRAINT employees_relationships_id_fk REFERENCES employees ON DELETE CASCADE,
	projectId  INTEGER     NOT NULL CONSTRAINT project_relationships_id_fk REFERENCES projects ON DELETE CASCADE,
	initiatorEmployeeId  INTEGER     NOT NULL CONSTRAINT initiator_relationships_id_fk REFERENCES employees ON DELETE CASCADE,
	notificationType  NOTIFICATION_TYPE NOT NULL,
	read BOOLEAN NOT NULL DEFAULT false,
	notificationCreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT check_initiatorId_is_same_as_employeeId CHECK (initiatorEmployeeId <> employeeId)
);

