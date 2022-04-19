create table CA
(
    t_id    varchar(10) not null,
    c_id    varchar(10) not null,
    date    date        not null,
    address varchar(20) not null,
    primary key (t_id, c_id, date, address),
    constraint CA_ibfk_1
        foreign key (t_id) references teacher (id),
    constraint CA_ibfk_2
        foreign key (c_id) references course (id)
)
    charset = utf8;

create index c_id
    on CA (c_id);

