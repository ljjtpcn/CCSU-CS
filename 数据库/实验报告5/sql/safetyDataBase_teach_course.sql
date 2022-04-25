CREATE TABLE teach_course
(
    teacherno char(6) NOT NULL,
    courseno  char(6) NOT NULL,
    PRIMARY KEY (teacherno, courseno)
);

INSERT INTO safetyDataBase.teach_course (teacherno, courseno) VALUES ('t05001', 'c05109');
INSERT INTO safetyDataBase.teach_course (teacherno, courseno) VALUES ('t05001', 'c05127');
INSERT INTO safetyDataBase.teach_course (teacherno, courseno) VALUES ('t05002', 'c05222');
INSERT INTO safetyDataBase.teach_course (teacherno, courseno) VALUES ('t05003', 'c05124');
INSERT INTO safetyDataBase.teach_course (teacherno, courseno) VALUES ('t05011', 'c05127');
INSERT INTO safetyDataBase.teach_course (teacherno, courseno) VALUES ('t05011', 'c05138');
