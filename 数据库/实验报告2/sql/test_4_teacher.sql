create table teacher
(
    teacherNo  char(12)    not null
        primary key,
    tname      varchar(6)  null,
    major      varchar(20) null,
    prof       char(10)    null,
    department varchar(20) null
);

INSERT INTO test_4.teacher (teacherNo, tname, major, prof, department) VALUES ('t05001', '苏超然', '软件工程', '教授', '计数院');
INSERT INTO test_4.teacher (teacherNo, tname, major, prof, department) VALUES ('t05002', '常斌', '会计学', '助教', '经管院');
INSERT INTO test_4.teacher (teacherNo, tname, major, prof, department) VALUES ('t05003', '孙石安', '网络安全', '教授', '计数院');
INSERT INTO test_4.teacher (teacherNo, tname, major, prof, department) VALUES ('t05011', '卢先', '软件工程', '副教授', '计数院');
INSERT INTO test_4.teacher (teacherNo, tname, major, prof, department) VALUES ('t05013', '李明', '机械制造', '讲师', '机电院');
INSERT INTO test_4.teacher (teacherNo, tname, major, prof, department) VALUES ('t05014', '王石', '软件工程', null, '计数院');
