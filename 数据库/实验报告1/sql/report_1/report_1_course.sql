create table course
(
    id     varchar(10) not null
        primary key,
    name   varchar(20) null,
    count  smallint    null,
    credit smallint    null
)
    charset = utf8;

