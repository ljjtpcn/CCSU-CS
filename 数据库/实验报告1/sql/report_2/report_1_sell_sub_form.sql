create table sub_form
(
    id           varchar(20) not null
        primary key,
    number       bigint      null,
    form_id      varchar(20) null,
    commodity_id varchar(20) null,
    constraint sub_form_ibfk_1
        foreign key (form_id) references form (id),
    constraint sub_form_ibfk_2
        foreign key (commodity_id) references commodity (id)
);

create index commodity_id
    on sub_form (commodity_id);

create index form_id
    on sub_form (form_id);

