create table stu_new
(
    studentNo char(12)    not null,
    course_no varchar(20) not null,
    total     double      null,
    primary key (studentNo, course_no),
    constraint stu_new_ibfk_1
        foreign key (course_no) references course (courseNo)
);

create index course_no
    on stu_new (course_no);

INSERT INTO test_4.stu_new (studentNo, course_no, total) VALUES ('18122221324', 'c05103', 91);
INSERT INTO test_4.stu_new (studentNo, course_no, total) VALUES ('18122221324', 'c05109', 89);
INSERT INTO test_4.stu_new (studentNo, course_no, total) VALUES ('18122221324', 'c05127', 93.4);
INSERT INTO test_4.stu_new (studentNo, course_no, total) VALUES ('18125111109', 'c05108', 77.6);
INSERT INTO test_4.stu_new (studentNo, course_no, total) VALUES ('18125111109', 'c05127', 88.6);
INSERT INTO test_4.stu_new (studentNo, course_no, total) VALUES ('18125111109', 'c05138', 80);
INSERT INTO test_4.stu_new (studentNo, course_no, total) VALUES ('18125121107', 'c05103', 75.2);
INSERT INTO test_4.stu_new (studentNo, course_no, total) VALUES ('18125121107', 'c05127', 77);
INSERT INTO test_4.stu_new (studentNo, course_no, total) VALUES ('18125121107', 'c05138', 74.2);
INSERT INTO test_4.stu_new (studentNo, course_no, total) VALUES ('18137156732', 'c05124', 88.2);
INSERT INTO test_4.stu_new (studentNo, course_no, total) VALUES ('18137156732', 'c05222', 79.4);
