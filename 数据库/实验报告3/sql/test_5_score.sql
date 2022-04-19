create table score
(
    studentno char(12)                not null comment '学号',
    courseno  char(6)                 not null comment '课程号',
    daily     float(3, 1) default 0.0 null comment '平时成绩',
    final     float(3, 1) default 0.0 null comment '期末成绩',
    primary key (studentno, courseno)
);

INSERT INTO test_5.score (studentno, courseno, daily, final) VALUES ('18122221324', 'c05103', 87, 92);
INSERT INTO test_5.score (studentno, courseno, daily, final) VALUES ('18122221324', 'c05109', 85, 90);
INSERT INTO test_5.score (studentno, courseno, daily, final) VALUES ('18122221324', 'c05127', 95, 93);
INSERT INTO test_5.score (studentno, courseno, daily, final) VALUES ('18125111109', 'c05108', 76, 78);
INSERT INTO test_5.score (studentno, courseno, daily, final) VALUES ('18125111109', 'c05127', 91, 88);
INSERT INTO test_5.score (studentno, courseno, daily, final) VALUES ('18125111109', 'c05138', 80, 80);
INSERT INTO test_5.score (studentno, courseno, daily, final) VALUES ('19125121107', 'c05108', 88, 72);
INSERT INTO test_5.score (studentno, courseno, daily, final) VALUES ('19125121107', 'c05127', 81, 76);
INSERT INTO test_5.score (studentno, courseno, daily, final) VALUES ('19125121107', 'c05138', 75, 74);
INSERT INTO test_5.score (studentno, courseno, daily, final) VALUES ('19137156732', 'c05124', 89, 88);
INSERT INTO test_5.score (studentno, courseno, daily, final) VALUES ('19137156732', 'c05222', 81, 79);
