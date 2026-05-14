-- A Task is defined by the following:
--  an integer created by the DB and returned to the user
--  a complete flag, default to false
--  the title of the task
--  the description of the task
--  the due date of the task
--  the priority, which will be rated 1-3 (1 being low priority, 3 being the highest priority)

CREATE TABLE IF NOT EXISTS TASKS (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    COMPLETE BOOLEAN DEFAULT FALSE,
    TITLE VARCHAR(50),
    DESCRIPTION VARCHAR(100),
    DUE_DATE DATE NOT NULL,
    PRIORITY INTEGER NOT NULL
);
