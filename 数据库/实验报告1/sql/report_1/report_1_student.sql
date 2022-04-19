create table student
(
    id            varchar(10) not null
        primary key,
    name          varchar(20) null,
    sex           varchar(20) null,
    birthday      datetime    null,
    phone         varchar(20) null,
    profession    varchar(20) null,
    department_id varchar(20) null,
    constraint student_ibfk_1
        foreign key (department_id) references department (id)
)
    charset = utf8;

create index department_id
    on student (department_id);

