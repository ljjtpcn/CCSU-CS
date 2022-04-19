create table form
(
    id        varchar(20) not null
        primary key,
    time      datetime    null,
    client_id varchar(10) null,
    constraint form_ibfk_1
        foreign key (client_id) references client (id)
);

create index client_id
    on form (client_id);

