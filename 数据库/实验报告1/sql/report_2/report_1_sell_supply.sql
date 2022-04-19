create table supply
(
    supplier_id  varchar(10) not null,
    commodity_id varchar(10) not null,
    number       bigint      null,
    time         datetime    null,
    primary key (supplier_id, commodity_id),
    constraint supply_ibfk_1
        foreign key (supplier_id) references supplier (id),
    constraint supply_ibfk_2
        foreign key (commodity_id) references commodity (id)
);

create index commodity_id
    on supply (commodity_id);

