create table teacher
(
    teacherno  char(6)  not null comment '教师号'
        primary key,
    tname      char(8)  not null comment '教师姓名',
    major      char(10) null comment '专业',
    prof       char(10) null comment '职称',
    department char(16) null comment '部门'
);

INSERT INTO test_5.teacher (teacherno, tname, major, prof, department) VALUES ('t05001', '苏超然', '软件工程', '教授', '计数院');
INSERT INTO test_5.teacher (teacherno, tname, major, prof, department) VALUES ('t05002', '常斌', '会计学', '助教', '经管院');
INSERT INTO test_5.teacher (teacherno, tname, major, prof, department) VALUES ('t05003', '孙石安', '网络安全', '教授', '计数院');
INSERT INTO test_5.teacher (teacherno, tname, major, prof, department) VALUES ('t05011', '卢先', '软件工程', '副教授', '计数院');
INSERT INTO test_5.teacher (teacherno, tname, major, prof, department) VALUES ('t05013', '李明', '机械制造', '讲师', '机电院');
INSERT INTO test_5.teacher (teacherno, tname, major, prof, department) VALUES ('t05014', '王石', '软件工程', null, '计数院');
