create table academy
(
    id      varchar(10) not null
        primary key,
    name    varchar(20) null,
    address varchar(20) null,
    constraint id
        unique (id)
)
    charset = utf8;

