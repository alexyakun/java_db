DROP TABLE IF EXISTS STUDENTS;
DROP TABLE IF EXISTS MARKS;
DROP TABLE IF EXISTS STUDENTS_MARKS;
CREATE TABLE STUDENTS(
    ID BIGINT PRIMARY KEY,
    SURNAME VARCHAR(255),
    NAME VARCHAR(255),
    OTCHESTVO VARCHAR(255),
    GROUP_NAME VARCHAR(255),
    YEAR_IN INTEGER
);
CREATE TABLE MARKS(
    ID BIGINT PRIMARY KEY,
    COURSE_NAME VARCHAR(255),
    MARK INTEGER
);
CREATE TABLE STUDENTS_MARKS(
    ID BIGINT PRIMARY KEY,
    STUDENT_ID BIGINT NOT NULL,
    MARKS_ID BIGINT NOT NULL,
    UNIQUE(STUDENT_ID, MARKS_ID),
    FOREIGN KEY  (STUDENT_ID) REFERENCES STUDENTS(ID),
    FOREIGN KEY (MARKS_ID) REFERENCES MARKS(ID)
);