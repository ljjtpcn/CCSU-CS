CREATE TABLE student
(
    studentNo char(12)    NULL COMMENT '学号',
    sname     char(8)     NULL COMMENT '姓名',
    sex       char(2)     NULL COMMENT '性别',
    birthdate date        NULL COMMENT '出生日期',
    entrance  int         NULL COMMENT '入学成绩',
    phone     char(11)    NULL COMMENT '电话号码',
    email     varchar(20) NULL COMMENT '邮件号码'
);

INSERT INTO safetyDataBase.student (studentNo, sname, sex, birthdate, entrance, phone, email) VALUES ('18122221324', '何白露', '女', '2000-12-04', 879, '13786112345', 'heyy@sina.com');
INSERT INTO safetyDataBase.student (studentNo, sname, sex, birthdate, entrance, phone, email) VALUES ('18125111109', '敬横江', '男', '2000-03-01', 789, '15678945623', 'jing@sina.com');
INSERT INTO safetyDataBase.student (studentNo, sname, sex, birthdate, entrance, phone, email) VALUES ('18125121107', '王伟', '男', '1999-09-12', 790, '13786188347', '3267888@QQ.com');
INSERT INTO safetyDataBase.student (studentNo, sname, sex, birthdate, entrance, phone, email) VALUES ('18137156732', '吴英', '女', '2000-10-19', 888, '13055568618', '17865@QQ.com');
