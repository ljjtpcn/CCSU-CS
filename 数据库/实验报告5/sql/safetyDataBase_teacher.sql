CREATE TABLE teacher
(
    teacherno  char(6)  NOT NULL COMMENT '教师号'
        PRIMARY KEY,
    tname      char(8)  NOT NULL COMMENT '教师姓名',
    major      char(10) NULL COMMENT '专业',
    prof       char(10) NULL COMMENT '职称',
    department char(16) NULL COMMENT '部门'
);

INSERT INTO safetyDataBase.teacher (teacherno, tname, major, prof, department) VALUES ('t05001', '苏超然', '软件工程', '教授', '计算机学院');
INSERT INTO safetyDataBase.teacher (teacherno, tname, major, prof, department) VALUES ('t05002', '常斌', '会计学', '助教', '管理学院');
INSERT INTO safetyDataBase.teacher (teacherno, tname, major, prof, department) VALUES ('t05003', '孙石安', '网络安全', '教授', '计算机学院');
INSERT INTO safetyDataBase.teacher (teacherno, tname, major, prof, department) VALUES ('t05011', '卢先', '软件工程', '副教授', '计算机学院');
INSERT INTO safetyDataBase.teacher (teacherno, tname, major, prof, department) VALUES ('t05013', '李明', '机械制造', '讲师', '机械学院');
