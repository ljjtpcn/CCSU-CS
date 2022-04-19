create table student
(
    studentNO      char(12)    not null
        primary key,
    sname          char(8)     null,
    sex            char(2)     null,
    birthdate      date        null,
    phone          char(11)    null,
    email          varchar(20) null,
    departmentName varchar(20) null,
    entrance       int         null
);

INSERT INTO test_4.student (studentNO, sname, sex, birthdate, phone, email, departmentName, entrance) VALUES ('18122221324', '何白露', '女', '2000-12-04', '13786112345', 'heyy@sina.com', '机电院', 879);
INSERT INTO test_4.student (studentNO, sname, sex, birthdate, phone, email, departmentName, entrance) VALUES ('18125111109', '敬横江', '男', '2000-03-01', '15678945623', 'jing@sina.com', '计数院', 789);
INSERT INTO test_4.student (studentNO, sname, sex, birthdate, phone, email, departmentName, entrance) VALUES ('18125121107', '王伟', '男', '1999-09-12', '13786188347', '3267888@QQ.com', '计数院', 790);
INSERT INTO test_4.student (studentNO, sname, sex, birthdate, phone, email, departmentName, entrance) VALUES ('18137156732', '吴英', '女', '2000-10-19', '13055568618', '17865@QQ.com', '经管院', 888);
