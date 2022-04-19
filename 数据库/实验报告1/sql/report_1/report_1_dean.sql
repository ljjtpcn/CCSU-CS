create table dean
(
    id         varchar(10) not null
        primary key,
    name       varchar(20) null,
    phone      varchar(20) null,
    academy_id varchar(20) null,
    constraint academy_id
        unique (academy_id),
    constraint dean_ibfk_1
        foreign key (academy_id) references academy (id)
)
    charset = utf8;

