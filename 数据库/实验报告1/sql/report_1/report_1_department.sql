create table department
(
    id         varchar(10) not null
        primary key,
    name       varchar(20) null,
    phone      varchar(20) null,
    address    varchar(20) null,
    academy_id varchar(20) null,
    constraint department_ibfk_1
        foreign key (academy_id) references academy (id)
)
    charset = utf8;

create index academy_id
    on department (academy_id);

