create table course
(
    courseNo char(6)     not null
        primary key,
    cname    varchar(20) null,
    type     char(8)     null,
    cpno     char(6)     null,
    period int null,
    exp      int         null,
    term     int         null
);

INSERT INTO test_4.course (courseNo, cname, type, cpno, period, exp, term) VALUES ('c05103', '电子技术', '选修', null, 48, 16, 2);
INSERT INTO test_4.course (courseNo, cname, type, cpno, period, exp, term) VALUES ('c05107', '程序设计基础', '必修', null, 48, 16, 4);
INSERT INTO test_4.course (courseNo, cname, type, cpno, period, exp, term) VALUES ('c05108', '数据库原理', '必修', 'c05107', 48, 16, 3);
INSERT INTO test_4.course (courseNo, cname, type, cpno, period, exp, term) VALUES ('c05109', 'C语言', '必修', 'c05108', 48, 16, 4);
INSERT INTO test_4.course (courseNo, cname, type, cpno, period, exp, term) VALUES ('c05124', '经济学', '必修', null, 64, 16, 4);
INSERT INTO test_4.course (courseNo, cname, type, cpno, period, exp, term) VALUES ('c05127', 'Java语言', '必修', 'c05109', 64, 16, 4);
INSERT INTO test_4.course (courseNo, cname, type, cpno, period, exp, term) VALUES ('c05138', '软件工程', '必修', 'c05109', 48, 8, 5);
INSERT INTO test_4.course (courseNo, cname, type, cpno, period, exp, term) VALUES ('c05222', '会计实务', '必修', null, 48, 8, 2);
INSERT INTO test_4.course (courseNo, cname, type, cpno, period, exp, term) VALUES ('c05223', 'UML_SOFT', '选修', 'c05224', 48, 8, 2);
INSERT INTO test_4.course (courseNo, cname, type, cpno, period, exp, term) VALUES ('c05224', 'UML设计', '选修', 'c05138', 64, 8, 2);
