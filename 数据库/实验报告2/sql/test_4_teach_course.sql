create table teach_course
(
    teacher_no char(6)  not null,
    course_no  char(6)  null,
    teachtime  datetime not null,
    primary key (teacher_no, teachtime),
    constraint teach_course_ibfk_1
        foreign key (teacher_no) references teacher (teacherNo),
    constraint teach_course_ibfk_2
        foreign key (course_no) references course (courseNo)
);

create index course_no
    on teach_course (course_no);

INSERT INTO test_4.teach_course (teacher_no, course_no, teachtime) VALUES ('t05001', 'c05109', '2022-03-25 14:00:00');
INSERT INTO test_4.teach_course (teacher_no, course_no, teachtime) VALUES ('t05001', 'c05109', '2022-03-29 10:00:00');
INSERT INTO test_4.teach_course (teacher_no, course_no, teachtime) VALUES ('t05003', 'c05124', '2022-03-11 16:00:00');
INSERT INTO test_4.teach_course (teacher_no, course_no, teachtime) VALUES ('t05001', 'c05127', '2022-03-24 08:00:00');
INSERT INTO test_4.teach_course (teacher_no, course_no, teachtime) VALUES ('t05011', 'c05127', '2022-03-25 16:00:00');
INSERT INTO test_4.teach_course (teacher_no, course_no, teachtime) VALUES ('t05011', 'c05127', '2022-03-27 10:00:00');
