create table score
(
    student_no char(12)    not null,
    course_no  char(6)     not null,
    daily      float(3, 1) null,
    final      float(3, 1) null,
    primary key (student_no, course_no),
    constraint score_ibfk_1
        foreign key (student_no) references student (studentNO),
    constraint score_ibfk_2
        foreign key (course_no) references course (courseNo)
);

create index course_no
    on score (course_no);

INSERT INTO test_4.score (student_no, course_no, daily, final) VALUES ('18122221324', 'c05103', 87, 92);
INSERT INTO test_4.score (student_no, course_no, daily, final) VALUES ('18122221324', 'c05109', 85, 90);
INSERT INTO test_4.score (student_no, course_no, daily, final) VALUES ('18122221324', 'c05127', 95, 93);
INSERT INTO test_4.score (student_no, course_no, daily, final) VALUES ('18125111109', 'c05108', 76, 78);
INSERT INTO test_4.score (student_no, course_no, daily, final) VALUES ('18125111109', 'c05127', 91, 88);
INSERT INTO test_4.score (student_no, course_no, daily, final) VALUES ('18125111109', 'c05138', 80, 80);
INSERT INTO test_4.score (student_no, course_no, daily, final) VALUES ('18125121107', 'c05103', 88, 72);
INSERT INTO test_4.score (student_no, course_no, daily, final) VALUES ('18125121107', 'c05127', 81, 76);
INSERT INTO test_4.score (student_no, course_no, daily, final) VALUES ('18125121107', 'c05138', 75, 74);
INSERT INTO test_4.score (student_no, course_no, daily, final) VALUES ('18137156732', 'c05124', 89, 88);
INSERT INTO test_4.score (student_no, course_no, daily, final) VALUES ('18137156732', 'c05222', 81, 79);
