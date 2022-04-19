create table teacher
(
    id            varchar(10) not null
        primary key,
    name          varchar(20) null,
    education     varchar(20) null,
    post          varchar(20) null,
    salary        int         null,
    phone         varchar(20) null,
    department_id varchar(20) null,
    constraint teacher_ibfk_1
        foreign key (department_id) references department (id)
)
    charset = utf8;

create index department_id
    on teacher (department_id);

