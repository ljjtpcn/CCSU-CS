CREATE TABLE score
(
    studentno char(12)                NOT NULL COMMENT '学号',
    courseno  char(6)                 NOT NULL COMMENT '课程号',
    daily     float(3, 1) DEFAULT 0.0 NULL COMMENT '平时成绩',
    final     float(3, 1) DEFAULT 0.0 NULL COMMENT '期末成绩',
    PRIMARY KEY (studentno, courseno)
);

INSERT INTO safetyDataBase.score (studentno, courseno, daily, final) VALUES ('18122221324', 'c05103', 87, 92);
INSERT INTO safetyDataBase.score (studentno, courseno, daily, final) VALUES ('18122221324', 'c05109', 85, 90);
INSERT INTO safetyDataBase.score (studentno, courseno, daily, final) VALUES ('18122221324', 'c05127', 95, 93);
INSERT INTO safetyDataBase.score (studentno, courseno, daily, final) VALUES ('18125111109', 'c05108', 78, 78);
INSERT INTO safetyDataBase.score (studentno, courseno, daily, final) VALUES ('18125111109', 'c05127', 91, 88);
INSERT INTO safetyDataBase.score (studentno, courseno, daily, final) VALUES ('18125111109', 'c05138', 80, 80);
INSERT INTO safetyDataBase.score (studentno, courseno, daily, final) VALUES ('18125121107', 'c05103', 88, 72);
INSERT INTO safetyDataBase.score (studentno, courseno, daily, final) VALUES ('18125121107', 'c05127', 81, 76);
INSERT INTO safetyDataBase.score (studentno, courseno, daily, final) VALUES ('18125121107', 'c05138', 75, 74);
INSERT INTO safetyDataBase.score (studentno, courseno, daily, final) VALUES ('18137156732', 'c05103', 89, 88);
INSERT INTO safetyDataBase.score (studentno, courseno, daily, final) VALUES ('18137156732', 'c05108', 75, 68);
INSERT INTO safetyDataBase.score (studentno, courseno, daily, final) VALUES ('18137156732', 'c05127', 81, 79);
