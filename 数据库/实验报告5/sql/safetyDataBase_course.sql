CREATE TABLE course
(
    courseno char(6)     NOT NULL COMMENT '课程号'
        PRIMARY KEY,
    cname    varchar(20) NULL COMMENT '课程名',
    type     char(8)     NOT NULL COMMENT '课程类型',
    PERIOD INT (2) NOT NULL COMMENT '总学时',
    exp      int(2)      NOT NULL COMMENT '实验学时',
    term     int(2)      NOT NULL COMMENT '开课学期'
);

INSERT INTO safetyDataBase.course (courseno, cname, type, period, exp, term) VALUES ('c05103', '电子技术', '必修', 64, 16, 2);
INSERT INTO safetyDataBase.course (courseno, cname, type, period, exp, term) VALUES ('c05108', '数据库原理', '必修', 48, 16, 3);
INSERT INTO safetyDataBase.course (courseno, cname, type, period, exp, term) VALUES ('c05109', 'C语言', '必修', 48, 16, 4);
INSERT INTO safetyDataBase.course (courseno, cname, type, period, exp, term) VALUES ('c05124', '操作系统', '必修', 64, 16, 4);
INSERT INTO safetyDataBase.course (courseno, cname, type, period, exp, term) VALUES ('c05127', '数据结构', '必修', 64, 16, 3);
INSERT INTO safetyDataBase.course (courseno, cname, type, period, exp, term) VALUES ('c05138', '软件工程', '必修', 48, 8, 5);
INSERT INTO safetyDataBase.course (courseno, cname, type, period, exp, term) VALUES ('c05222', '会计实务', '必修', 48, 8, 2);
