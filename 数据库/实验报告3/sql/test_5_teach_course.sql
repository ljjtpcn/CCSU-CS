create table teach_course
(
    teacherno char(6)  not null,
    courseno  char(6)  not null,
    teachtime datetime not null,
    primary key (teacherno, courseno, teachtime)
);

INSERT INTO test_5.teach_course (teacherno, courseno, teachtime) VALUES ('t05001', 'c05109', '2021-02-08 10:00:00');
INSERT INTO test_5.teach_course (teacherno, courseno, teachtime) VALUES ('t05001', 'c05109', '2021-03-10 14:00:00');
INSERT INTO test_5.teach_course (teacherno, courseno, teachtime) VALUES ('t05001', 'c05127', '2021-03-09 08:00:00');
INSERT INTO test_5.teach_course (teacherno, courseno, teachtime) VALUES ('t05003', 'c05124', '2021-03-11 16:00:00');
INSERT INTO test_5.teach_course (teacherno, courseno, teachtime) VALUES ('t05011', 'c05127', '2021-03-13 16:00:00');
INSERT INTO test_5.teach_course (teacherno, courseno, teachtime) VALUES ('t05011', 'c05127', '2021-03-15 10:00:00');
