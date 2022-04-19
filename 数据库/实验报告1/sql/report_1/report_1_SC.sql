create table SC
(
    s_id  varchar(10) not null,
    c_id  varchar(10) not null,
    grade smallint    not null,
    primary key (s_id, c_id),
    constraint SC_ibfk_1
        foreign key (s_id) references student (id),
    constraint SC_ibfk_2
        foreign key (c_id) references course (id)
)
    charset = utf8;

create index c_id
    on SC (c_id);

