create table course
(
    courseno char(6)     not null comment '课程号'
        primary key,
    cname    varchar(20) null comment '课程名',
    type     char(8)     not null comment '课程类型',
    cpno     char(6)     null comment '先行课程',
    period int (2) not null comment '总学时',
    exp      int(2)      not null comment '实验学时',
    term     int(2)      not null comment '开课学期'
);

INSERT INTO test_5.course (courseno, cname, type, cpno, period, exp, term) VALUES ('c05103', '电子技术', '选修', null, 48, 16, 2);
INSERT INTO test_5.course (courseno, cname, type, cpno, period, exp, term) VALUES ('c05107', '程序设计基础', '必修', null, 48, 16, 4);
INSERT INTO test_5.course (courseno, cname, type, cpno, period, exp, term) VALUES ('c05108', '数据库原理', '必修', 'c05107', 48, 16, 3);
INSERT INTO test_5.course (courseno, cname, type, cpno, period, exp, term) VALUES ('c05109', 'C语言', '必修', 'c05108', 48, 16, 4);
INSERT INTO test_5.course (courseno, cname, type, cpno, period, exp, term) VALUES ('c05124', '经济学', '必修', null, 64, 16, 4);
INSERT INTO test_5.course (courseno, cname, type, cpno, period, exp, term) VALUES ('c05127', 'Java语言', '必修', 'c05109', 64, 16, 4);
INSERT INTO test_5.course (courseno, cname, type, cpno, period, exp, term) VALUES ('c05138', '软件工程', '必修', 'c05109', 48, 8, 5);
INSERT INTO test_5.course (courseno, cname, type, cpno, period, exp, term) VALUES ('c05222', '会计实务', '必修', null, 48, 8, 2);
INSERT INTO test_5.course (courseno, cname, type, cpno, period, exp, term) VALUES ('c05223', 'UML_SOFT', '选修', 'c05224', 48, 8, 2);
INSERT INTO test_5.course (courseno, cname, type, cpno, period, exp, term) VALUES ('c05224', 'UML设计', '选修', 'c05138', 64, 8, 2);
