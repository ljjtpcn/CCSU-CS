create table student
(
    studentNo      char(12)    null comment '学号',
    sname          char(8)     null comment '姓名',
    sex            char(2)     null comment '性别',
    birthdate      date        null comment '出生日期',
    entrance       int         null comment '入学成绩',
    phone          char(11)    null comment '电话号码',
    email          varchar(20) null comment '邮件号码',
    departmentname varchar(20) null comment '所在系名'
);

INSERT INTO test_5.student (studentNo, sname, sex, birthdate, entrance, phone, email, departmentname) VALUES ('18122221324', '何白露', '女', '2001-12-04', 879, '13786112345', 'heyy@sina.com', '机电院');
INSERT INTO test_5.student (studentNo, sname, sex, birthdate, entrance, phone, email, departmentname) VALUES ('18125111109', '敬横江', '男', '2000-03-01', 789, '15678945623', 'jing@sina.com', '计数院');
INSERT INTO test_5.student (studentNo, sname, sex, birthdate, entrance, phone, email, departmentname) VALUES ('19125121107', '王伟', '男', '1999-09-12', 790, '13786188347', '3267888@QQ.com', '计数院');
INSERT INTO test_5.student (studentNo, sname, sex, birthdate, entrance, phone, email, departmentname) VALUES ('19137156732', '吴英', '女', '2000-10-19', 888, '13055568618', '17865@QQ.com', '经管院');
